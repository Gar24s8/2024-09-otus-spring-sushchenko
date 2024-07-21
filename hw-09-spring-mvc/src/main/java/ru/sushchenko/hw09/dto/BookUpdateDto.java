package ru.sushchenko.hw09.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookUpdateDto {

    @NotNull
    private Long id;

    @NotBlank(message = "This field can't be empty")
    @Size(min = 1, max = 120, message = "The title must be between 1 and 120 characters long")
    private String title;


    @NotNull(message = "This field can't be empty")
    private Long authorId;

    @NotNull(message = "This field can't be empty")
    private Long genreId;

    public static BookUpdateDto fromBookDto(BookDto bookDto) {
        return new BookUpdateDto(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthor() == null ? null : bookDto.getAuthor().getId(),
                bookDto.getGenre() == null ? null : bookDto.getGenre().getId());
    }
}
