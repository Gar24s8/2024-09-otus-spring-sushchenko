package ru.sushchenko.hw07.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw07.models.Genre;
import ru.sushchenko.hw07.repositories.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
