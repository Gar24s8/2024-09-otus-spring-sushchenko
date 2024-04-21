package ru.sushchenko.hw05.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw05.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
