package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {
    private UUID uuid;
    private String name;
    private int age;

    public AuthorDTO(UUID uuid, String name, int age) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    public AuthorDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
