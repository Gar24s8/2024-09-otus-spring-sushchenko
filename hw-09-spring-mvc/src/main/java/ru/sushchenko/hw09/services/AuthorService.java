package ru.sushchenko.hw09.services;

import ru.sushchenko.hw09.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();
}
