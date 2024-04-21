package ru.sushchenko.hw05.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.hw05.models.Genre;
import ru.sushchenko.hw05.repositories.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
