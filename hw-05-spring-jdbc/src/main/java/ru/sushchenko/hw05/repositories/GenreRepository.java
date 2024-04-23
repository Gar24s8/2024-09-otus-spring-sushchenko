package ru.sushchenko.hw05.repositories;

import ru.sushchenko.hw05.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
