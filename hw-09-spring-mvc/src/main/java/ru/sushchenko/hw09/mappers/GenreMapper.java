package ru.sushchenko.hw09.mappers;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw09.dto.GenreDto;
import ru.sushchenko.hw09.models.Genre;

@Component
public class GenreMapper {

    public Genre toModel(GenreDto dto) {
        return new Genre(dto.getId(), dto.getName());
    }

    public GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
