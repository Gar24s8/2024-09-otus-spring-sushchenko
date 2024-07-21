package ru.sushchenko.hw09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sushchenko.hw09.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
