package ru.sushchenko.hw08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.sushchenko.hw08.models.Author;
import ru.sushchenko.hw08.models.Book;
import ru.sushchenko.hw08.models.Comment;
import ru.sushchenko.hw08.models.Genre;
import ru.sushchenko.hw08.repositories.AuthorRepository;
import ru.sushchenko.hw08.repositories.BookRepository;
import ru.sushchenko.hw08.repositories.CommentRepository;
import ru.sushchenko.hw08.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final List<Author> authors = Arrays.asList(new Author(null, "J.R.R. Tolkien"),
            new Author(null, "D.Keyes"));

    private final List<Genre> genres = Arrays.asList(new Genre(null, "Sci-Fi Novel"),
            new Genre(null, "Fantasy"));

    private List<Book> books = new ArrayList<>();

    @ChangeSet(order = "001", id = "dropDb", author = "gar24s8", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insert authors", author = "gar24s8")
    public void insertAuthors(AuthorRepository authorRepository) {
        authors.forEach(authorRepository::save);
    }

    @ChangeSet(order = "003", id = "insert genres", author = "gar24s8")
    public void insertGenres(GenreRepository genreRepository) {
        genres.forEach(genreRepository::save);
    }

    @ChangeSet(order = "004", id = "insert books", author = "gar24s8")
    public void insertBooks(BookRepository bookRepository) {
        books.add(new Book(null, "The Silmarillion", authors.get(0), genres.get(1)));
        books.add(new Book(null, "Flowers for Algernon", authors.get(1), genres.get(0)));
        books.forEach(bookRepository::save);
    }

    @ChangeSet(order = "005", id = "insert comments", author = "gar24s8")
    public void insertComments(CommentRepository commentRepository) {
        commentRepository.save(
                new Comment(null, "The Silmarillion is the most staggering achievement of fantasy"
                        , books.get(0)));
    }
}
