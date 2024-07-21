package ru.sushchenko.hw09.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sushchenko.hw09.dto.AuthorDto;
import ru.sushchenko.hw09.dto.BookCreateDto;
import ru.sushchenko.hw09.dto.BookDto;
import ru.sushchenko.hw09.dto.BookUpdateDto;
import ru.sushchenko.hw09.dto.GenreDto;
import ru.sushchenko.hw09.exceptions.EntityNotFoundException;
import ru.sushchenko.hw09.services.AuthorServiceImpl;
import ru.sushchenko.hw09.services.BookServiceImpl;
import ru.sushchenko.hw09.services.GenreServiceImpl;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorServiceImpl authorService;

    @MockBean
    private GenreServiceImpl genreService;

    @Test
    void save_ShouldSaveNewBook_WhenBookNotExists() throws Exception {
        BookDto book = getBookDto();

        BookCreateDto bookCreateDto = new BookCreateDto(book.getTitle(), book.getAuthor().getId(),
                book.getGenre().getId());

        mvc.perform(post("/book").flashAttr("book", bookCreateDto))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void getBook_ShouldReturnBookById_WhenExists() throws Exception {
        BookDto bookDto = new BookDto(1L, "book",
                new AuthorDto(1L, "author"), new GenreDto(1L, "genre"));

        when(bookService.findById(1L)).thenReturn(bookDto);

        mvc.perform(get("/book/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getException_ShouldReturnEntityNotFoundException_whenBookNotExists() throws Exception {
        when(bookService.findById(1L))
                .thenThrow(new EntityNotFoundException(null));

        mvc.perform(get("/book/").param("id", String.valueOf(1L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBook_ShouldUpdateBook_WhenExists() throws Exception {
        BookDto book = getBookDto();
        given(bookService.findById(book.getId())).willReturn(book);

        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), book.getGenre().getId());

        Long id = 1L;
        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void getException_ShouldReturnEntityNotFoundException_whenUpdateFailed() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(null, null,
                null, null);
        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @Test
    void getValidIdAuthorException() throws Exception {
        BookDto book = getBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                null, book.getGenre().getId());
        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @Test
    void getValidIGenreException() throws Exception {
        BookDto book = getBookDto();
        BookUpdateDto bookUpdateDto = new BookUpdateDto(book.getId(), book.getTitle(),
                book.getAuthor().getId(), null);

        Long id = 1L;

        mvc.perform(post("/book/{id}", id).flashAttr("book", bookUpdateDto))
                .andExpect(redirectedUrl("/book/1"));
    }

    @Test
    void delete_ShouldDeleteBook_WhenExists() throws Exception {
        mvc.perform(post("/delete").param("id", String.valueOf(1L)))
                .andExpect(redirectedUrl("/"));
    }

    private BookDto getBookDto() {
        when(authorService.findAll()).thenReturn(List.of(new AuthorDto(1L, "author")));
        when(genreService.findAll()).thenReturn(List.of(new GenreDto(1L, "genre")));
        return new BookDto(1L, "title",
                authorService.findAll().get(0),
                genreService.findAll().get(0));
    }
}