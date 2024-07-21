package ru.sushchenko.hw09.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Long id;

    @NotNull(message = "This field can't be empty")
    private String fullName;
}
