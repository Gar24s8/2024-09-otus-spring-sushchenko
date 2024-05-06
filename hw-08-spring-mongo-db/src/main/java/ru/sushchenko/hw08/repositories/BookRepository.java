package ru.sushchenko.hw08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sushchenko.hw08.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

    List<Book> findAll();

    void deleteById(String id);

}
