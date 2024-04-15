package ru.sushchenko.hw04.service;

import ru.sushchenko.hw04.domain.Student;
import ru.sushchenko.hw04.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);


}
