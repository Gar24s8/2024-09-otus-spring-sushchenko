package ru.sushchenko.hw08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sushchenko.hw08.models.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
