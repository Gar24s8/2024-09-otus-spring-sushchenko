package ru.sushchenko.hw07.commands;

import lombok.RequiredArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;

@RequiredArgsConstructor
@ShellComponent
public class H2ConsoleCommands {

    @ShellMethod(value = "Open h2 console", key = "h2")
    public void h2console() throws SQLException {
        Console.main();
    }
}
