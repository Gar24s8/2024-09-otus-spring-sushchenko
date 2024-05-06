package ru.sushchenko.hw08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("books")
public class Book {

    @Id
    private String id;

    @Field(name = "title")
    private String title;

    @Field(name = "author")
    private Author author;

    @Field(name = "genre")
    private Genre genre;
}
