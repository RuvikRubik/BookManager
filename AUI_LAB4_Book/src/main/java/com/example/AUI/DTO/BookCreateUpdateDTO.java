package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateUpdateDTO {
    private String title;
    private int pages;
    private UUID authorId;
    private String authorName;
}