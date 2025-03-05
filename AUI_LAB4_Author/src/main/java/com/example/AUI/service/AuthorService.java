package com.example.AUI.service;

import com.example.AUI.entity.Author;
import com.example.AUI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> findAuthorById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public boolean deleteAuthor(UUID id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
