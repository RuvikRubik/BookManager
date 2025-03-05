package com.example.AUI.service;

import com.example.AUI.entity.Book;
import com.example.AUI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public Optional<Book> findBookById(UUID id) {
        return bookRepository.findById(id);
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public boolean deleteBook(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> findBooksByAuthorId(UUID authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    @Transactional
    public void deleteBooksByAuthor(UUID authorId) {
        bookRepository.deleteByAuthorId(authorId);
    }
}
