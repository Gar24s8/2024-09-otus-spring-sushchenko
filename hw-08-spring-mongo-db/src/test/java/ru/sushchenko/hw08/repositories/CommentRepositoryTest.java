package ru.sushchenko.hw08.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sushchenko.hw08.models.Book;
import ru.sushchenko.hw08.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CommentRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    public void init() {
        var book1 = new Book("1", "BookTitle1", null, null);
        mongoTemplate.save(book1);

        var book2 = new Book("2", "BookTitle2", null, null);
        mongoTemplate.save(book2);

        var book3 = new Book("3", "BookTitle3", null, null);
        mongoTemplate.save(book3);

        mongoTemplate.save(new Comment("1", "Comment1", book1));

        mongoTemplate.save(new Comment("2", "Comment2", book3));
    }

    @Test
    void save_ShouldAddNewComment_WhenNothingToUpdate() {
        var book1 = mongoTemplate.findById("1", Book.class);
        var addedComment = new Comment(null, "Comment text", book1);

        commentRepository.save(addedComment);

        var foundComment = mongoTemplate.findById(addedComment.getId(), Comment.class);

        assertThat(addedComment).isEqualTo(foundComment);
    }

    @Test
    void save_ShouldUpdateExistingComment_WhenFound() {
        var book3 = mongoTemplate.findById("3", Book.class);
        var existingComment = mongoTemplate.findById("2", Comment.class);
        var updatedComment = commentRepository.save(new Comment("2", "Updated Comment", book3));
        var foundComment = mongoTemplate.findById("2", Comment.class);

        assertThat(updatedComment)
                .isNotEqualTo(existingComment)
                .isEqualTo(foundComment);
    }

    @Test
    void findById_ShouldReturnComment_WhenExists() {
        var expected = mongoTemplate.findById("1", Comment.class);
        var actual = commentRepository.findById("1");

        assertThat(actual).isPresent().get().isEqualTo(expected);
    }
}