package ru.sushchenko.hw09.services;

import ru.sushchenko.hw09.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(Long id);

    List<CommentDto> findCommentsByBookId(Long bookId);

    CommentDto create(String commentText, Long bookId);

    CommentDto update(Long id, String commentText);

    void deleteById(Long id);
}
