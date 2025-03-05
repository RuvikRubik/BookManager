package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookListDTO {
    private UUID uuid;
    private String title;

    public BookListDTO(UUID uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookCollectionDTO{uuid=" + uuid + ", title='" + title + "'}";
    }
}