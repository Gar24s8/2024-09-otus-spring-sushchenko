package ru.sushchenko.hw09.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sushchenko.hw09.models.Book;
import ru.sushchenko.hw09.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaCommentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findById_ShouldReturnExpectedComment_WhenExists() {
        var actualComment = commentRepository.findById(1L);
        var expectedComment = testEntityManager.find(Comment.class, 1L);

        assertThat(actualComment).isPresent().get().isEqualTo(expectedComment);
    }

    @Test
    void findAllCommentsByBookId_ShouldReturnCommentsForBook_WhenFound() {
        var comments = commentRepository.findAllCommentsByBookId(1L);

        assertThat(comments).isNotNull().hasSize(2)
                .allMatch(s -> !s.getCommentText().equals(""))
                .anyMatch(s -> s.getCommentText().equals("Comment_2"))
                .anyMatch(s -> s.getCommentText().equals("Comment_3"));
    }

    @Test
    void save_ShouldUpdateExistingComment_WhenFound() {
        var expectedComment = new Comment(1L, "new_Comment", testEntityManager.find(Book.class, 1L));
        commentRepository.save(expectedComment);

        var actualComment = testEntityManager.find(Comment.class, 1L);

        assertThat(actualComment)
                .matches(c -> c.getId() == 1L &&
                        c.getCommentText().equals("new_Comment") &&
                        c.getBook().getId() == 1L);
    }

    @Test
    void save_ShouldSaveNewBook_WhenNothingToUpdate() {
        var expectedComment = new Comment(4L, "new_Comment", testEntityManager.find(Book.class, 2L));
        commentRepository.save(expectedComment);

        var actualComment = testEntityManager.find(Comment.class, 4L);

        assertThat(actualComment)
                .matches(c -> c.getId() == 4L &&
                        c.getCommentText().equals("new_Comment") &&
                        c.getBook().getId() == 2L);
    }

    @Test
    void deleteById_ShouldDeleteComment_WhenExists() {
        assertThat(commentRepository.findById(1L)).isPresent();
        commentRepository.deleteById(1L);
        assertThat(commentRepository.findById(1L)).isEmpty();
    }

}