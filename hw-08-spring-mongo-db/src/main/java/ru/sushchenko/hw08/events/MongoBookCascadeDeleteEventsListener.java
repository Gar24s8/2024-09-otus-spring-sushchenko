package ru.sushchenko.hw08.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.sushchenko.hw08.models.Book;
import ru.sushchenko.hw08.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        var bookId = String.valueOf(event.getSource().get("_id"));
        commentRepository.deleteByBookId(bookId);
    }

}
