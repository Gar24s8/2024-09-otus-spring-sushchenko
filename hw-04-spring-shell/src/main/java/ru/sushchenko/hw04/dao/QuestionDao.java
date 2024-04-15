package ru.sushchenko.hw04.dao;

import ru.sushchenko.hw04.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
