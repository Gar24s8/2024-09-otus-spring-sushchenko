
package ru.sushchenko.hw07.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw07.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaAuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private List<Author> authors;

    @BeforeEach
    public void setUp() {
        authors = List.of(new Author(1L, "Author_1")
                , new Author(2L, "Author_2")
                , new Author(3L, "Author_3"));
    }

    @Test
    void findAll_ShouldFindAllAuthors_WhenExists() {
        var actualAuthors = authorRepository.findAll();
        var expectedAuthors = authors;
        assertThat(actualAuthors).containsExactlyElementsOf(expectedAuthors);
    }

    @Test
    void findById_ShouldFindAuthorById_WhenExists() {
        var foundAuthor = authorRepository.findById(1L);
        var expectedAuthor = testEntityManager.find(Author.class, 1L);
        assertThat(foundAuthor).isPresent().get().isEqualTo(expectedAuthor);
    }

}
