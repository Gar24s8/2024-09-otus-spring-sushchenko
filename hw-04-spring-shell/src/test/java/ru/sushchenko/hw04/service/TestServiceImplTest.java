package ru.sushchenko.hw04.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sushchenko.hw04.dao.QuestionDao;
import ru.sushchenko.hw04.domain.Answer;
import ru.sushchenko.hw04.domain.Question;
import ru.sushchenko.hw04.domain.Student;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class TestServiceImplTest {

    @MockBean
    private QuestionDao dao;

    @MockBean
    private LocalizedIOService ioService;

    @Autowired
    private TestService testService;

    private Student getStudent() {
        return new Student("Name", "Surname");
    }

    @Test
    void executeTestFor_ShouldReturnTestResultWithCorrectAnswers_WhenAllAnswersAreCorrect() {

        var expectedCorrectAnswers = 3;

        Mockito.when(dao.findAll()).thenReturn(getQuestions());
        Mockito.when(ioService.readIntForRange(anyInt(), anyInt(), anyString())).thenReturn(1);

        var testResult = testService.executeTestFor(getStudent());
        var actualCorrectAnswers = testResult.getRightAnswersCount();

        assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
    }

    @Test
    void executeTestFor_ShouldNotReturnTestResult_WhenStudentIsNull() {
        var testResult = testService.executeTestFor(null);
        Mockito.when(dao.findAll()).thenReturn(getQuestions());
        Mockito.when(ioService.readIntForRange(anyInt(), anyInt(), anyString())).thenReturn(1);

        var actualCorrectAnswers = testResult.getRightAnswersCount();

        assertEquals(0, actualCorrectAnswers);
    }

    private List<Question> getQuestions() {
        var questionFirst = new Question("Question1",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        var questionSecond = new Question("Question2",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        var questionThird = new Question("Question3",
                Arrays.asList(new Answer("1", true), new Answer("2", false)));
        return Arrays.asList(questionFirst, questionSecond, questionThird);
    }
}