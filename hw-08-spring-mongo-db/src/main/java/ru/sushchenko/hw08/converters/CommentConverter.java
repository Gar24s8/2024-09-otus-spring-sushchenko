package ru.sushchenko.hw08.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sushchenko.hw08.models.Comment;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final BookConverter bookConverter;

    public String commentToString(Comment comment) {
        return "Id: %s, text: %s, book: {%s}".formatted(
                comment.getId(),
                comment.getCommentText(),
                bookConverter.bookToString(comment.getBook())
        );
    }

}
