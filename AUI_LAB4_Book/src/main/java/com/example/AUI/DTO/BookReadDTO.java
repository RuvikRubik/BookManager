package com.example.AUI.DTO;

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
    private UUID authorId;
    private String authorName;

    public BookReadDTO(UUID uuid, String title, int pages, UUID authorId, String authorName) {
        this.uuid = uuid;
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BookReadDTO{title='" + title + "', pages=" + pages + ", authorName='" + authorName + "'}";
    }
}