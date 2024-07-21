package ru.sushchenko.hw09.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sushchenko.hw09.dto.GenreDto;
import ru.sushchenko.hw09.services.GenreServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreServiceImpl genreService;

    @GetMapping("/genres")
    public String genresList(Model model) {
        List<GenreDto> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genres/genres_list";

    }
}
