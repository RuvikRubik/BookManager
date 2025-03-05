package com.example.AUI.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public Author(UUID uuid, String name, int age) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
    }

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Author)) {
            return false;
        } else {
            Author author = (Author)obj;
            return this.age == author.age && Objects.equals(this.name, author.name);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.age});
    }

    public String toString() {
        return "Author{name='" + this.name + "', age=" + this.age + "}";
    }
}
