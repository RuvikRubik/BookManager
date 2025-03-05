package com.example.AUI.repository;

import com.example.AUI.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthorId(UUID authorId);

    void deleteByAuthorId(UUID authorId);
}