
package ru.sushchenko.hw09.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw09.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaAuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAll_ShouldFindAllAuthors_WhenExists() {
        var authors = authorRepository.findAll();

        assertThat(authors).isNotNull().hasSize(3)
                .allMatch(s -> !s.getFullName().equals(""))
                .anyMatch(s -> s.getFullName().equals("Author_1") && s.getId() == 1);
    }

    @Test
    void findById_ShouldFindAuthorById_WhenExists() {
        var foundAuthor = authorRepository.findById(1L);
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        assertThat(foundAuthor).isPresent().get().isEqualTo(expectedAuthor);
    }

}
