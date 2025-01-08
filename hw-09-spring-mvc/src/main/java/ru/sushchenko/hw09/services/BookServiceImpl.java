package ru.sushchenko.hw09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw09.dto.BookCreateDto;
import ru.sushchenko.hw09.dto.BookDto;
import ru.sushchenko.hw09.dto.BookUpdateDto;
import ru.sushchenko.hw09.exceptions.EntityNotFoundException;
import ru.sushchenko.hw09.mappers.BookMapper;
import ru.sushchenko.hw09.repositories.AuthorRepository;
import ru.sushchenko.hw09.repositories.BookRepository;
import ru.sushchenko.hw09.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id = %d is not found".formatted(id))));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto create(BookCreateDto bookDto) {
        final Long authorId = bookDto.getAuthorId();
        final Long genreId = bookDto.getGenreId();

        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = bookMapper.toModel(bookDto, author, genre);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public BookDto update(BookUpdateDto bookDto) {
        final Long id = bookDto.getId();
        final Long authorId = bookDto.getAuthorId();
        final Long genreId = bookDto.getGenreId();

        bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Book with id %d not found".formatted(id)));

        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = bookMapper.toModel(bookDto, author, genre);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
