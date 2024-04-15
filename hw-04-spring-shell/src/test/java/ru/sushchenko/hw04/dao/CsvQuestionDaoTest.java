package ru.sushchenko.hw04.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sushchenko.hw04.config.AppProperties;
import ru.sushchenko.hw04.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class CsvQuestionDaoTest {

    @MockBean
    private AppProperties appProperties;

    @Autowired
    private QuestionDao dao;

    @BeforeEach
    void setUp() {
        dao = new CsvQuestionDao(appProperties);
    }

    @Test
    void findAll_ShouldReturnExpectedSize_WhenExists() {
        int expectedRows = 5;
        when(appProperties.getTestFileName()).thenReturn("testQuestions.csv");
        int actualRows = dao.findAll().size();

        assertEquals(expectedRows, actualRows);
    }

    @Test
    void findAll_ShouldThrowQuestionReadException_WhenCsvFileIsInvalid() {
        when(appProperties.getTestFileName()).thenReturn("invalid.csv");

        assertThrows(QuestionReadException.class, () -> dao.findAll());
    }


}