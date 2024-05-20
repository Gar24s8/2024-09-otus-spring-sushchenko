package ru.sushchenko.hw06.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw06.models.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
