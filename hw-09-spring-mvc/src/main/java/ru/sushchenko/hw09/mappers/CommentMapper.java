package ru.sushchenko.hw09.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sushchenko.hw09.dto.CommentDto;
import ru.sushchenko.hw09.models.Book;
import ru.sushchenko.hw09.models.Comment;

@Component
@AllArgsConstructor
public class CommentMapper {

    public Comment toModel(CommentDto dto, Book book) {
        return new Comment(dto.getId(), dto.getCommentText(),
                book);
    }

    public CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getCommentText(),
                comment.getBook().getId());
    }
}
