package ru.sushchenko.hw06.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sushchenko.hw06.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaAuthorRepository.class})
class JpaAuthorRepositoryTest {

    @Autowired
    private JpaAuthorRepository jpaAuthorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAll_ShouldFindAllAuthors_WhenExists() {
        var authors = jpaAuthorRepository.findAll();
        assertThat(authors).isNotNull().hasSize(3)
                .allMatch(s -> !s.getFullName().equals(""))
                .anyMatch(s -> s.getFullName().equals("Author_1") && s.getId() == 1);
    }

    @Test
    void findById_ShouldFindAuthorById_WhenExists() {
        var foundAuthor = jpaAuthorRepository.findById(1L);
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        assertThat(foundAuthor).isPresent().get().isEqualTo(expectedAuthor);
    }

}