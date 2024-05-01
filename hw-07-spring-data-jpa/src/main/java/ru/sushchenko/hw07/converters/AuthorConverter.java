package ru.sushchenko.hw07.converters;

import org.springframework.stereotype.Component;
import ru.sushchenko.hw07.models.Author;

@Component
public class AuthorConverter {

    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
