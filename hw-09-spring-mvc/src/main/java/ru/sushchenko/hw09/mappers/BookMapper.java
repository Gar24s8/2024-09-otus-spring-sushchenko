package ru.sushchenko.hw09.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sushchenko.hw09.dto.BookCreateDto;
import ru.sushchenko.hw09.dto.BookDto;
import ru.sushchenko.hw09.dto.BookUpdateDto;
import ru.sushchenko.hw09.models.Author;
import ru.sushchenko.hw09.models.Book;
import ru.sushchenko.hw09.models.Genre;

@Component
@AllArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    public Book toModel(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(),
                authorMapper.toModel(dto.getAuthor()),
                genreMapper.toModel(dto.getGenre()));
    }

    public Book toModel(BookCreateDto dto, Author author, Genre genre) {
        return new Book(null, dto.getTitle(), author, genre);
    }

    public Book toModel(BookUpdateDto dto, Author author, Genre genre) {
        return new Book(dto.getId(), dto.getTitle(),
                author, genre);
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(),
                authorMapper.toDto(book.getAuthor()),
                genreMapper.toDto(book.getGenre()));
    }
}
