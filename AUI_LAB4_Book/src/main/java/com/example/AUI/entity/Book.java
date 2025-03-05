package com.example.AUI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "title")
    private String title;
    @Column(name = "pages")
    private int pages;
    @Column(name = "author_id")
    private UUID authorId;
    @Column(name = "author_name")
    private String authorName;

    public Book(UUID uuid, String title, int pages, UUID authorId, String authorName) {
        this.uuid = uuid;
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Book(String title, int pages, UUID authorId, String authorName) {
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Book)) {
            return false;
        } else {
            Book book = (Book)obj;
            return this.pages == book.pages && Objects.equals(this.title, book.title);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.title, this.pages});
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', pages=" + pages + ", authorId=" + authorId + "}";
    }
}
