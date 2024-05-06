package ru.sushchenko.hw08.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sushchenko.hw08.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class GenreRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    GenreRepository genreRepository;

    @BeforeEach
    public void init() {
        mongoTemplate.save(new Genre("1", "Genre 1"));
    }

    @Test
    void findById_ShouldReturnGenre_WhenFound() {
        var expectedGenre = mongoTemplate.findById("1", Genre.class);
        var actualGenre = genreRepository.findById("1");

        assertThat(actualGenre).isPresent().get().isEqualTo(expectedGenre);
    }

}