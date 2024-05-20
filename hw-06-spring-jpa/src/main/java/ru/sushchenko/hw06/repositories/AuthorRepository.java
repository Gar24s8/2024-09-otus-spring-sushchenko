package ru.sushchenko.hw06.repositories;

import ru.sushchenko.hw06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(long id);
}
