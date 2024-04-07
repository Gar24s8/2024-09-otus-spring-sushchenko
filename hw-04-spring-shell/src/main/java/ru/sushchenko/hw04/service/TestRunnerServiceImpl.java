package ru.sushchenko.hw04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.sushchenko.hw04.domain.Student;
import ru.sushchenko.hw04.domain.TestResult;

@ShellComponent
@RequiredArgsConstructor
public class TestRunnerServiceImpl {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    private Student student;

    private TestResult testResult;

    @ShellMethod(value = "Run test", key = {"s", "start"})
    public void run() {
        login();
        test();
        result();
    }

    @ShellMethod(value = "Login", key = {"l", "login"})
    public String login() {
        student = studentService.determineCurrentStudent();
        testResult = null;
        return "Student: " + student.getFullName();
    }

    @ShellMethod(value = "Test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isStudentLogin")
    public String test() {
        testResult = testService.executeTestFor(student);
        return "Test result";
    }

    @ShellMethod(value = "Result", key = {"r", "result"})
    @ShellMethodAvailability(value = "isCompletedTest")
    public String result() {
        resultService.showResult(testResult);
        return "Result";
    }

    private Availability isStudentLogin() {
        return student == null
                ? Availability.unavailable("Can't find student, please login")
                : Availability.available();
    }

    private Availability isCompletedTest() {
        return testResult == null
                ? Availability.unavailable("Please complete test")
                : Availability.available();
    }
}
