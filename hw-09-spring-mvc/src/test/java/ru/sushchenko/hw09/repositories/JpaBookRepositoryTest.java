package ru.sushchenko.hw09.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw09.models.Author;
import ru.sushchenko.hw09.models.Book;
import ru.sushchenko.hw09.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaBookRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    private List<Author> authors;

    private List<Genre> genres;

    @BeforeEach
    void setUp() {
        authors = List.of(new Author(1L, "Author_1")
                , new Author(2L, "Author_2")
                , new Author(3L, "Author_3"));

        genres = List.of(new Genre(1L, "Genre_1")
                , new Genre(2L, "Genre_2")
                , new Genre(3L, "Genre_3"));
    }

    @Test
    void findById_ShouldReturnBookById_WhenExists() {
        var actualBook = bookRepository.findById(1L);
        var expectedBook = testEntityManager.find(Book.class, 1L);
        assertThat(actualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @Test
    void findAll_ShouldReturnListOfAllBooks_WhenExists() {
        var actualBooks = bookRepository.findAll();
        assertThat(actualBooks).isNotNull().hasSize(3);
    }

    @Test
    void save_ShouldUpdateExistingBook_WhenFound() {
        var expectedBook = new Book(1L, "new_BookTitle", authors.get(0), genres.get(0));

        bookRepository.save(expectedBook);

        var actualBook = testEntityManager.find(Book.class, 1L);

        assertThat(actualBook)
                .matches(b -> b.getTitle().equals("new_BookTitle"))
                .matches(b -> b.getAuthor().getFullName().equals(authors.get(0).getFullName()))
                .matches(b -> b.getGenre().getName().equals(genres.get(0).getName()));
    }

    @Test
    void save_ShouldSaveNewBook_WhenNothingToUpdate() {
        var expectedBook = new Book(4L, "new_BookTitle", authors.get(0), genres.get(0));

        bookRepository.save(expectedBook);

        var actualBook = testEntityManager.find(Book.class, 4L);

        assertThat(actualBook)
                .matches(b -> b.getTitle().equals("new_BookTitle"))
                .matches(b -> b.getAuthor().getFullName().equals(authors.get(0).getFullName()))
                .matches(b -> b.getGenre().getName().equals(genres.get(0).getName()));
    }

    @Test
    void deleteById_ShouldDeleteBook_WhenExists() {
        assertThat(bookRepository.findById(1L)).isPresent();
        bookRepository.deleteById(1L);
        assertThat(bookRepository.findById(1L)).isEmpty();
    }

}