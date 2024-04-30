package ru.sushchenko.hw06.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sushchenko.hw06.models.Author;
import ru.sushchenko.hw06.models.Book;
import ru.sushchenko.hw06.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaBookRepository.class, JpaGenreRepository.class})
class JpaBookRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private JpaBookRepository jpaBookRepository;

    private List<Author> authors;

    private List<Genre> genres;

    private List<Book> books;

    @BeforeEach
    void setUp() {
        authors = List.of(new Author(1L, "Author_1")
                , new Author(2L, "Author_2")
                , new Author(3L, "Author_3"));

        genres = List.of(new Genre(1L, "Genre_1")
                , new Genre(2L, "Genre_2")
                , new Genre(3L, "Genre_3"));

        books = List.of(new Book(1L, "BookTitle_1", testEntityManager.find(Author.class, 1L), testEntityManager.find(Genre.class, 1L))
                , new Book(2L, "BookTitle_2", testEntityManager.find(Author.class, 2L), testEntityManager.find(Genre.class, 2L))
                , new Book(3L, "BookTitle_3", testEntityManager.find(Author.class, 3L), testEntityManager.find(Genre.class, 3L)));
    }

    @Test
    void findById_ShouldReturnBookById_WhenExists() {
        var actualBook = jpaBookRepository.findById(1L);
        var expectedBook = testEntityManager.find(Book.class, 1L);
        assertThat(actualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @Test
    void findAll_ShouldReturnListOfAllBooks_WhenExists() {
        var actualBooks = jpaBookRepository.findAll();
        var expectedBooks = books;
        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
    }

    @Test
    void save_ShouldUpdateExistingBook_WhenFound() {
        var expectedBook = new Book(1L, "new_BookTitle", authors.get(0), genres.get(0));

        assertThat(jpaBookRepository.findById(expectedBook.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedBook);

        var returnedBook = jpaBookRepository.save(expectedBook);

        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .isEqualTo(expectedBook);

        assertThat(jpaBookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedBook);
    }

    @Test
    void save_ShouldSaveNewBook_WhenNothingToUpdate() {
        var expectedBook = new Book(1L, "new_BookTitle", authors.get(0), genres.get(0));
        var returnedBook = jpaBookRepository.save(expectedBook);

        assertThat(returnedBook).isNotNull().isEqualTo(expectedBook);
        assertThat(jpaBookRepository.findById(returnedBook.getId())).isPresent().get().isEqualTo(returnedBook);
    }

    @Test
    void deleteById_ShouldDeleteBook_WhenExists() {
        assertThat(jpaBookRepository.findById(1L)).isPresent();
        jpaBookRepository.deleteById(1L);
        assertThat(jpaBookRepository.findById(1L)).isEmpty();
    }

}