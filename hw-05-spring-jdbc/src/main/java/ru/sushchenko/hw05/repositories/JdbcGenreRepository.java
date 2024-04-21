package ru.sushchenko.hw05.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.sushchenko.hw05.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.query("SELECT id, name FROM genres", new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        List<Genre> genre = namedParameterJdbcOperations.query("""
                SELECT id, name FROM genres WHERE id = :id
                """, param, new GenreRowMapper());
        if (genre.isEmpty()) {
            return Optional.empty();
        }
        return genre.stream().findFirst();
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            final long id = rs.getLong("id");
            final String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
