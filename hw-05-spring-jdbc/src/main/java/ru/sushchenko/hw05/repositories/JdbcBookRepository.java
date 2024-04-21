package ru.sushchenko.hw05.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.sushchenko.hw05.exceptions.EntityNotFoundException;
import ru.sushchenko.hw05.models.Author;
import ru.sushchenko.hw05.models.Book;
import ru.sushchenko.hw05.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        List<Book> books = namedParameterJdbcOperations.query("""
                SELECT b.id, b.title, b.author_id, b.genre_id, g.id, g.name, a.id, a.full_name
                FROM books b
                INNER JOIN authors a ON b.author_id=a.id
                INNER JOIN genres g ON b.genre_id=g.id
                WHERE b.id = :id
                """, param, new BookRowMapper());
        if (books.isEmpty()) {
            return Optional.empty();
        }
        return books.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.query("""
                SELECT b.id, b.title, b.author_id, b.genre_id, a.id, a.full_name, g.id, g.name,
                        FROM books b
                        INNER JOIN authors a ON b.author_id=a.id
                        INNER JOIN genres g ON b.genre_id=g.id
                """, new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("DELETE FROM books WHERE id=:id", param);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource
                (Map.of("title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
        namedParameterJdbcOperations.update("""
                INSERT INTO books (title, author_id, genre_id)
                VALUES (:title, :author_id, :genre_id)"""
                , parameterSource, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
        int update = namedParameterJdbcOperations.update("""
                UPDATE books
                SET title = :title, author_id = :author_id, genre_id=:genre_id
                WHERE id = :id
                """, parameterSource);
        if (update == 0) {
            throw new EntityNotFoundException("Failed to update book with id = %d".formatted(book.getId()));
        }
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getLong("authors.id"));
            author.setFullName(rs.getString("authors.full_name"));

            Genre genre = new Genre();
            genre.setId(rs.getLong("genres.id"));
            genre.setName(rs.getString("genres.name"));

            Book book = new Book();
            book.setId(rs.getLong("books.id"));
            book.setTitle(rs.getString("books.title"));
            book.setAuthor(author);
            book.setGenre(genre);

            return book;
        }
    }
}
