package ru.sushchenko.hw09.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sushchenko.hw09.dto.AuthorDto;
import ru.sushchenko.hw09.services.AuthorServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @GetMapping("/authors")
    public String authorsList(Model model) {
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authors/authors_list";
    }
}
