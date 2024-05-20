package ru.sushchenko.hw08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sushchenko.hw08.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
