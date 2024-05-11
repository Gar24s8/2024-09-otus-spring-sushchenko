package ru.sushchenko.hw07.services;

import ru.sushchenko.hw07.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    List<Comment> findCommentsByBookId(long bookId);

    Comment create(String commentText, long bookId);

    Comment update(long id, String commentText);

    void deleteById(long id);
}
