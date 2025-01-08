package ru.sushchenko.hw09.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sushchenko.hw09.dto.BookDto;
import ru.sushchenko.hw09.dto.CommentDto;
import ru.sushchenko.hw09.services.BookServiceImpl;
import ru.sushchenko.hw09.services.CommentServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    private final BookServiceImpl bookService;

    @GetMapping("/book/{id}/comments")
    public String getCommentsForBook(@PathVariable("id") long id, Model model) {
        BookDto book = bookService.findById(id);
        List<CommentDto> comments = commentService.findCommentsByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("comments", comments);

        return "comments/book_comments";
    }
}
