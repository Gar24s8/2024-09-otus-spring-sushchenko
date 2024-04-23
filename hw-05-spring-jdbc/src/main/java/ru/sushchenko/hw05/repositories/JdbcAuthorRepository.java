package ru.sushchenko.hw05.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.sushchenko.hw05.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.query("SELECT id, full_name FROM authors", new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        List<Author> authors = namedParameterJdbcOperations.query("""
                SELECT id, full_name FROM authors WHERE id = :id
                """, param, new AuthorRowMapper());
        if (authors.isEmpty()) {
            return Optional.empty();
        }
        return authors.stream().findFirst();
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            final long id = rs.getLong("id");
            final String fullName = rs.getString("full_name");
            return new Author(id, fullName);
        }
    }
}
