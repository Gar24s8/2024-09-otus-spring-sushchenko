package ru.sushchenko.hw07.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw07.models.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }
}
