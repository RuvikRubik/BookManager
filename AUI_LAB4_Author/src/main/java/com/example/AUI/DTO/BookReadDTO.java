package com.example.AUI.DTO;

import com.example.AUI.entity.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookReadDTO {
    private UUID uuid;
    private String title;
    private int pages;

    public BookReadDTO(UUID uuid, String title, int pages) {
        this.uuid = uuid;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "BookReadDTO{title='" + title + "', pages=" + pages + "'}";
    }
}