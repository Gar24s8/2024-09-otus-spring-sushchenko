package ru.sushchenko.hw08.services;

import ru.sushchenko.hw08.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(String id);

}
