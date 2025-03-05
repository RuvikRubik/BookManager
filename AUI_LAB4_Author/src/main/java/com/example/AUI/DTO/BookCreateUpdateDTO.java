package com.example.AUI.DTO;

import com.example.AUI.entity.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateUpdateDTO {
    private String title;
    private int pages;
    private Author author;
}