package ru.sushchenko.hw08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sushchenko.hw08.models.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
