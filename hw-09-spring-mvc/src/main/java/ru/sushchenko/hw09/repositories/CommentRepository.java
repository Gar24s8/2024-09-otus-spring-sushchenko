package ru.sushchenko.hw09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sushchenko.hw09.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllCommentsByBookId(Long bookId);
}
