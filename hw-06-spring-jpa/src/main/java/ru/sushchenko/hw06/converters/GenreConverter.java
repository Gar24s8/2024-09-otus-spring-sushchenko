package ru.sushchenko.hw06.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw06.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
