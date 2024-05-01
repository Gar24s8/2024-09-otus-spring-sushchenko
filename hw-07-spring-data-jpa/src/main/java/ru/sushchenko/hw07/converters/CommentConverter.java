package ru.sushchenko.hw07.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sushchenko.hw07.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    private final BookConverter bookConverter;

    public String commentToString(Comment comment) {
        return "Id: %d, text: %s, book: {%s}".formatted(
                comment.getId(),
                comment.getCommentText(),
                bookConverter.bookToString(comment.getBook())
        );
    }
}
