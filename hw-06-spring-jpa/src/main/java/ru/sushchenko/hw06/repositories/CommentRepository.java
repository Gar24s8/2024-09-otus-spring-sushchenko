package ru.sushchenko.hw06.repositories;

import ru.sushchenko.hw06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    List<Comment> findAllCommentsByBookId(Long bookId);

    Comment save(Comment comment);

    void deleteById(long id);
}
