package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthorListDTO  {
    private UUID uuid;
    private String name;

    public AuthorListDTO (UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorCollectionDTO{uuid=" + uuid + ", name='" + name + "'}";
    }
}