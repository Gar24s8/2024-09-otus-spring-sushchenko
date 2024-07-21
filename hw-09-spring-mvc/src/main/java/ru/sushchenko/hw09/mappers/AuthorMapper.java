package ru.sushchenko.hw09.mappers;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw09.dto.AuthorDto;
import ru.sushchenko.hw09.models.Author;

@Component
public class AuthorMapper {

    public Author toModel(AuthorDto dto) {
        return new Author(dto.getId(), dto.getFullName());
    }

    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }
}
