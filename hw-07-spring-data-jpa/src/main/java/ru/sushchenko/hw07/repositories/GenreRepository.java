package ru.sushchenko.hw07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sushchenko.hw07.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
