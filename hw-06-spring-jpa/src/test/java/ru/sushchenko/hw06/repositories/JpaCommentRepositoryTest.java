package ru.sushchenko.hw06.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sushchenko.hw06.models.Book;
import ru.sushchenko.hw06.models.Comment;

import java.util.List;

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
        var foundComments = jpaCommentRepository.findAllCommentsByBookId(1L);
        var expectedComments = List.of(new Comment(2L, "Comment_2", testEntityManager.find(Book.class, 1L))
                , new Comment(3L, "Comment_3", testEntityManager.find(Book.class, 1L)));

        assertThat(foundComments).containsExactlyElementsOf(expectedComments);
    }

    @Test
    void save_ShouldUpdateExistingComment_WhenFound() {
        var expectedComment = new Comment(1L, "new_Comment", testEntityManager.find(Book.class, 1L));

        assertThat(jpaCommentRepository.findById(expectedComment.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedComment);

        var returnedComment = jpaCommentRepository.save(expectedComment);

        assertThat(returnedComment).isNotNull()
                .matches(book -> book.getId() > 0)
                .isEqualTo(expectedComment);

        assertThat(jpaCommentRepository.findById(returnedComment.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedComment);
    }

    @Test
    void save_ShouldSaveNewBook_WhenNothingToUpdate() {
        var expectedComment = new Comment(1L, "new_Comment", testEntityManager.find(Book.class, 1L));
        var returnedBook = jpaCommentRepository.save(expectedComment);

        assertThat(returnedBook).isNotNull().isEqualTo(expectedComment);
        assertThat(jpaCommentRepository.findById(returnedBook.getId())).isPresent().get().isEqualTo(returnedBook);
    }

    @Test
    void deleteById_ShouldDeleteComment_WhenExists() {
        assertThat(jpaCommentRepository.findById(1L)).isPresent();
        jpaCommentRepository.deleteById(1L);
        assertThat(jpaCommentRepository.findById(1L)).isEmpty();
    }

}