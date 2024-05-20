package ru.sushchenko.hw08.services;

import ru.sushchenko.hw08.models.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    void deleteById(String id);

}
