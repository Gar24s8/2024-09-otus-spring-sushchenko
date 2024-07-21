package ru.sushchenko.hw09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw09.dto.AuthorDto;
import ru.sushchenko.hw09.mappers.AuthorMapper;
import ru.sushchenko.hw09.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll()
                .stream().map(authorMapper::toDto)
                .collect(Collectors.toList());
    }
}
