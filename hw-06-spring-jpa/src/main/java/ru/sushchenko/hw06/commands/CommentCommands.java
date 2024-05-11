package ru.sushchenko.hw06.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.sushchenko.hw06.converters.CommentConverter;
import ru.sushchenko.hw06.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Find all comments for book with id", key = "fcbid")
    public String findAllCommentsByBookId(long bookId) {
        return commentService.findCommentsByBookId(bookId).stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find comment by id", key = "cbid")
    public String findById(long id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .orElse("Comment with id %d not found".formatted(id));
    }

    @ShellMethod(value = "Insert comment", key = "cins")
    public String createComment(String commentText, long bookId) {
        var comment = commentService.create(commentText, bookId);
        return commentConverter.commentToString(comment);
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(long id, String commentText) {
        var savedComment = commentService.update(id, commentText);
        return commentConverter.commentToString(savedComment);
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public void deleteComment(long id) {
        commentService.deleteById(id);
    }
}
