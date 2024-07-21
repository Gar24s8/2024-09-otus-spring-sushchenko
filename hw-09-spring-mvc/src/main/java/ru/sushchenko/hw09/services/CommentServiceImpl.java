package ru.sushchenko.hw09.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw09.dto.CommentDto;
import ru.sushchenko.hw09.exceptions.EntityNotFoundException;
import ru.sushchenko.hw09.mappers.CommentMapper;
import ru.sushchenko.hw09.models.Comment;
import ru.sushchenko.hw09.repositories.BookRepository;
import ru.sushchenko.hw09.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    @Override
    public CommentDto findById(Long id) {
        return commentMapper.toDto(commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id = %d is not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> findCommentsByBookId(Long bookId) {
        return commentRepository.findAllCommentsByBookId(bookId).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentDto create(String commentText, Long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(null, commentText, book);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Transactional
    @Override
    public CommentDto update(Long id, String commentText) {
        Comment currentComment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id %d not found".formatted(id)));

        currentComment.setCommentText(commentText);
        return commentMapper.toDto(commentRepository.save(currentComment));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
