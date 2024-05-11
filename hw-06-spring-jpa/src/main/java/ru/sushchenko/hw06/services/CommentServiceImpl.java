package ru.sushchenko.hw06.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw06.exceptions.EntityNotFoundException;
import ru.sushchenko.hw06.models.Comment;
import ru.sushchenko.hw06.repositories.BookRepository;
import ru.sushchenko.hw06.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long bookId) {
        return commentRepository.findAllCommentsByBookId(bookId);
    }

    @Override
    @Transactional
    public Comment create(String commentText, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("not found book with id = %d".formatted(bookId)));
        var comment = new Comment(0, commentText, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(long id, String commentText) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found comment with id = %d".formatted(id)));
        comment.setCommentText(commentText);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
