package ru.sushchenko.hw09.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

    private Long id;

    private String commentText;

    private Long bookId;
}
