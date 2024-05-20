package ru.sushchenko.hw07.services;

import ru.sushchenko.hw07.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
}
