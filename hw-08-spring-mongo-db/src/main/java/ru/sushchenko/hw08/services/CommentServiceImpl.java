package ru.sushchenko.hw08.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.hw08.exceptions.EntityNotFoundException;
import ru.sushchenko.hw08.models.Comment;
import ru.sushchenko.hw08.repositories.BookRepository;
import ru.sushchenko.hw08.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment create(String commentText, String bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %s not found".formatted(bookId)));
        var comment = new Comment(null, commentText, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(String id, String commentText, String bookId) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id %s not found".formatted(id)));
        comment.setCommentText(commentText);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

}
