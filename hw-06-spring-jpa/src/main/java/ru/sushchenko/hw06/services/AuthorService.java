package ru.sushchenko.hw06.services;

import ru.sushchenko.hw06.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
