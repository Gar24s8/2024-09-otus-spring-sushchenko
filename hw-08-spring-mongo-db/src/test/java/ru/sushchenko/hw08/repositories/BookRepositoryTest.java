package ru.sushchenko.hw08.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sushchenko.hw08.models.Author;
import ru.sushchenko.hw08.models.Book;
import ru.sushchenko.hw08.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    public void init() {
        final var tolkien = new Author("1", "J.R.R. Tolkien");
        final var fantasy = new Genre("1", "Fantasy");
        mongoTemplate.save(new Book("1", "The Silmarrilion", tolkien, fantasy));

        final var keyes = new Author("2", "D. Keyes");
        final var sciFi = new Genre("2", "Sci-Fi Novel");
        mongoTemplate.save(new Book("2", "Flowers for Algernon", keyes, sciFi));
    }

    @Test
    void findById_SHouldReturnBookById_WhenFound() {
        var expectedBook = mongoTemplate.findById("2", Book.class);
        var actualBook = bookRepository.findById("2");

        assertThat(actualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @Test
    void save_ShouldInsertNewBook_WhenNothingToUpdate() {
        var author = mongoTemplate.findById("2", Author.class);
        var genre = mongoTemplate.findById("2", Genre.class);
        var addedBook = new Book(null, "New Title", author, genre);

        bookRepository.save(addedBook);

        assertThat(addedBook.getId()).isNotEmpty();

        var foundBook = mongoTemplate.findById(addedBook.getId(), Book.class);

        assertThat(foundBook).isEqualTo(addedBook);
    }

    @Test
    void save_ShouldUpdateExistingBook_WhenFound() {
        var author = mongoTemplate.findById("2", Author.class);
        var genre = mongoTemplate.findById("2", Genre.class);
        var expectedBook = new Book("2", "New Title", author, genre);
        var actualBook = mongoTemplate.findById(expectedBook.getId(), Book.class);

        assertThat(actualBook).isNotEqualTo(expectedBook);

        var returnedBook = bookRepository.save(expectedBook);

        assertThat(returnedBook).isNotNull()
                .matches(book -> !book.getId().isEmpty())
                .isEqualTo(expectedBook);

        var foundBook = mongoTemplate.findById(returnedBook.getId(), Book.class);

        assertThat(foundBook).isEqualTo(returnedBook);
    }

}