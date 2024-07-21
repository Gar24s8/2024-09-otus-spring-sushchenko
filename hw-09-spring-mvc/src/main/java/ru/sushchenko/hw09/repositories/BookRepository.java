package ru.sushchenko.hw09.repositories;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sushchenko.hw09.models.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph("book-with-author-genre-entity-graph")
    List<Book> findAll();

    @Override
    @EntityGraph("book-with-author-genre-entity-graph")
    Optional<Book> findById(@Nonnull Long id);
}
