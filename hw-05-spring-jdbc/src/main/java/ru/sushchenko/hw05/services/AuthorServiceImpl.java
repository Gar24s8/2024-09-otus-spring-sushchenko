package ru.sushchenko.hw05.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.hw05.models.Author;
import ru.sushchenko.hw05.repositories.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
