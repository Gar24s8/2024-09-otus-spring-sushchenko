package ru.sushchenko.hw09.services;

import ru.sushchenko.hw09.dto.BookCreateDto;
import ru.sushchenko.hw09.dto.BookDto;
import ru.sushchenko.hw09.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookDto);

    BookDto update(BookUpdateDto bookDto);

    void deleteById(Long id);
}
