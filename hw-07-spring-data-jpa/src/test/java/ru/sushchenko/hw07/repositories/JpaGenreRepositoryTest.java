package ru.sushchenko.hw07.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw07.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaGenreRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void findAll_ShouldFindAllGenres_WhenExists() {
        var genres = genreRepository.findAll();

        assertThat(genres).isNotNull().hasSize(3)
                .allMatch(s -> !s.getName().equals(""))
                .anyMatch(s -> s.getName().equals("Genre_1") && s.getId() == 1);
    }

    @Test
    void findById_ShouldFindGenreById_WhenExists() {
        var foundGenre = genreRepository.findById(1L);
        var expectedGenre = testEntityManager.find(Genre.class, 1L);
        assertThat(foundGenre).isPresent().get().isEqualTo(expectedGenre);
    }
}