package ru.sushchenko.hw09.services;

import ru.sushchenko.hw09.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();
}
