package ru.sushchenko.hw09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw09.dto.GenreDto;
import ru.sushchenko.hw09.mappers.GenreMapper;
import ru.sushchenko.hw09.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }
}
