package ru.sushchenko.hw08.services;

import ru.sushchenko.hw08.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(String id);

    Comment create(String commentText, String bookId);

    Comment update(String id, String commentText, String bookId);

    void deleteById(String id);

    List<Comment> findCommentsByBookId(String bookId);

}
