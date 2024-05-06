package ru.sushchenko.hw08.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw08.models.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %s, Name: %s".formatted(genre.getId(), genre.getName());
    }

}
