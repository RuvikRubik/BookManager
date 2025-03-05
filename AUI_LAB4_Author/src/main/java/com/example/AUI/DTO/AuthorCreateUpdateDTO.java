package com.example.AUI.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCreateUpdateDTO {
    private String name;
    private int age;
}