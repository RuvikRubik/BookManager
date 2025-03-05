package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthorReadDTO {
    private UUID uuid;
    private String name;
    private int age;
    private List<BookReadDTO> books; // Dodajemy książki

    public AuthorReadDTO(UUID uuid, String name, Integer age, List<BookReadDTO> books) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public AuthorReadDTO(UUID uuid, String name, Integer age) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "AuthorReadDTO{name='" + name + "', age=" + age + ", bookTitles=" + "}";
    }
}