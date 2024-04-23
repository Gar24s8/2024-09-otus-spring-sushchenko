package ru.sushchenko.hw05.services;

import ru.sushchenko.hw05.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
