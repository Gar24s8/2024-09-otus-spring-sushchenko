package ru.sushchenko.hw07.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw07.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaGenreRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    private List<Genre> genres;

    @BeforeEach
    void setUp() {
        genres = List.of(new Genre(1L, "Genre_1")
                , new Genre(2L, "Genre_2")
                , new Genre(3L, "Genre_3"));
    }

    @Test
    void findAll_ShouldFindAllGenres_WhenExists() {
        var actualGenres = genreRepository.findAll();
        var expectedGenres = genres;
        assertThat(actualGenres).containsExactlyElementsOf(expectedGenres);
    }

    @Test
    void findById_ShouldFindGenreById_WhenExists() {
        var foundGenre = genreRepository.findById(1L);
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        assertThat(foundGenre).isPresent().get().isEqualTo(expectedGenre);
    }
}