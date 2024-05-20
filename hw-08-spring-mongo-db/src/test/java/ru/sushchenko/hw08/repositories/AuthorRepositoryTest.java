package ru.sushchenko.hw08.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sushchenko.hw08.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    public void init() {
        mongoTemplate.save(new Author("1", "testName_1"));
        mongoTemplate.save(new Author("2", "testName_2"));
    }

    @Test
    void findAll_ShouldReturnAllAuthors_WhenExists() {
        var expected = mongoTemplate.findAll(Author.class);
        var actual = authorRepository.findAll();

        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void findById_ShouldReturnAuthorWithId_WhenExists() {
        var expected = mongoTemplate.findById("2", Author.class);
        var actual = authorRepository.findById("2");

        assertThat(actual).isPresent().get().isEqualTo(expected);
    }
}