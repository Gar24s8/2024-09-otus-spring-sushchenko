package ru.sushchenko.hw06.services;

import ru.sushchenko.hw06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    List<Comment> findCommentsByBookId(long bookId);

    Comment create(String commentText, long bookId);

    Comment update(long id, String commentText, long bookId);

    void deleteById(long id);
}
