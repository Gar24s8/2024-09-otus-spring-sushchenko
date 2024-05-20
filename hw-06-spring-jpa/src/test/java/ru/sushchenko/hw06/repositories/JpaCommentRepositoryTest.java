package ru.sushchenko.hw06.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sushchenko.hw06.models.Book;
import ru.sushchenko.hw06.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaCommentRepository.class})
class JpaCommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private JpaCommentRepository jpaCommentRepository;

    @Test
    void findById_ShouldReturnExpectedComment_WhenExists() {
        var actualComment = jpaCommentRepository.findById(1);
        var expectedComment = testEntityManager.find(Comment.class, 1L);

        assertThat(actualComment).isPresent().get().isEqualTo(expectedComment);
    }

    @Test
    void findAllCommentsByBookId_ShouldReturnCommentsForBook_WhenFound() {
        var comments = jpaCommentRepository.findAllCommentsByBookId(1L);

        assertThat(comments).isNotNull().hasSize(2)
                .allMatch(s -> !s.getCommentText().equals(""))
                .anyMatch(s -> s.getCommentText().equals("Comment_2"))
                .anyMatch(s -> s.getCommentText().equals("Comment_3"));
    }

    @Test
    void save_ShouldUpdateExistingComment_WhenFound() {
        var expectedComment = new Comment(1L, "new_Comment", testEntityManager.find(Book.class, 1L));
        jpaCommentRepository.save(expectedComment);

        var actualComment = testEntityManager.find(Comment.class, 1L);

        assertThat(actualComment)
                .matches(c -> c.getId() == 1L &&
                        c.getCommentText().equals("new_Comment") &&
                        c.getBook().getId() == 1L);
    }

    @Test
    void save_ShouldSaveNewBook_WhenNothingToUpdate() {
        var expectedComment = new Comment(4L, "new_Comment", testEntityManager.find(Book.class, 2L));
        jpaCommentRepository.save(expectedComment);

        var actualComment = testEntityManager.find(Comment.class, 4L);

        assertThat(actualComment)
                .matches(c -> c.getId() == 4L &&
                        c.getCommentText().equals("new_Comment") &&
                        c.getBook().getId() == 2L);
    }

    @Test
    void deleteById_ShouldDeleteComment_WhenExists() {
        assertThat(jpaCommentRepository.findById(1L)).isPresent();
        jpaCommentRepository.deleteById(1L);
        assertThat(jpaCommentRepository.findById(1L)).isEmpty();
    }

}