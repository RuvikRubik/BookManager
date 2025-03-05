package com.example.AUI.controller;

import com.example.AUI.DTO.AuthorDTO;
import com.example.AUI.DTO.BookCreateUpdateDTO;
import com.example.AUI.DTO.BookListDTO;
import com.example.AUI.DTO.BookReadDTO;
import com.example.AUI.entity.Book;
import com.example.AUI.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final RestTemplate restTemplate;
    private final String AUTHOR_SERVICE_URL = "http://localhost:8082/author";

    public BookController(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    // GET all books (collection)

    @GetMapping
    public ResponseEntity<List<BookListDTO>> listBooks() {
        List<BookListDTO> books = bookService.findAllBooks().stream()
                .map(book -> new BookListDTO(book.getUuid(), book.getTitle(), book.getPages()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(books);
    }

    // GET a single book by ID

    @GetMapping("/{id}")
    public ResponseEntity<BookReadDTO> getBook(@PathVariable UUID id) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build(); // HTTP 404 jeśli książka nie istnieje
        }
        System.out.println(book.get().getAuthorId());
        String authorServiceUrl = "http://localhost:8082/author/" + book.get().getAuthorId();

        try {
            ResponseEntity<AuthorDTO> authorResponse = restTemplate.getForEntity(authorServiceUrl, AuthorDTO.class);

            if (authorResponse.getStatusCode().is2xxSuccessful() && authorResponse.getBody() != null) {
                AuthorDTO author = authorResponse.getBody();

                // Utwórz DTO z pełnymi informacjami
                BookReadDTO bookReadDTO = new BookReadDTO(
                        book.get().getUuid(),
                        book.get().getTitle(),
                        book.get().getPages(),
                        author.getUuid(),
                        author.getName() // Nazwa autora pobrana z mikroserwisu autorów
                );

                return ResponseEntity.ok(bookReadDTO);
            } else {
                // Jeżeli autor nie został znaleziony, zwróć błąd
                return ResponseEntity.badRequest().body(null);
            }
        } catch (RestClientException e) {
            // Zaloguj wyjątek w przypadku błędu połączenia
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500 w przypadku błędu połączenia
        }
    }

    // POST to create a new book

    @PostMapping
    public ResponseEntity<BookReadDTO> addBook(@RequestBody BookCreateUpdateDTO bookCreateDTO) {
        // Fetch the author by ID from the DTO
        String authorCheckUrl = AUTHOR_SERVICE_URL + "/" + bookCreateDTO.getAuthorId();
        System.out.println(authorCheckUrl);
        ResponseEntity<AuthorDTO> authorResponse = restTemplate.getForEntity(authorCheckUrl, AuthorDTO.class);
        if (!authorResponse.getStatusCode().is2xxSuccessful() || authorResponse.getBody() == null) {
            return ResponseEntity.badRequest().build(); // HTTP 400, jeśli autor nie istnieje
        }

        AuthorDTO author = authorResponse.getBody();

        // Create and save the new book
        Book newBook = new Book();
        newBook.setUuid(UUID.randomUUID());
        newBook.setTitle(bookCreateDTO.getTitle());
        newBook.setPages(bookCreateDTO.getPages());
        newBook.setAuthorId(author.getUuid());
        newBook.setAuthorName(author.getName());
        Book savedBook = bookService.saveBook(newBook);

        // Return the response as BookReadDTO
        return ResponseEntity.ok(new BookReadDTO(
                savedBook.getUuid(),
                savedBook.getTitle(),
                savedBook.getPages(),
                savedBook.getAuthorId(),
                savedBook.getAuthorName()
        ));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookListDTO>> getBooksByAuthor(@PathVariable UUID authorId) {
        List<BookListDTO> books = bookService.findBooksByAuthorId(authorId).stream()
                .map(book -> new BookListDTO(book.getUuid(), book.getTitle(),book.getPages()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    // PUT to update a book by ID
    public ResponseEntity<BookReadDTO> updateBook(@RequestBody BookCreateUpdateDTO bookUpdateDTO, @PathVariable UUID id) {
        // Sprawdź, czy autor istnieje w Author Management Service
        String authorCheckUrl = AUTHOR_SERVICE_URL + "/" + bookUpdateDTO.getAuthorId();
        ResponseEntity<AuthorDTO> authorResponse = restTemplate.getForEntity(authorCheckUrl, AuthorDTO.class);

        if (!authorResponse.getStatusCode().is2xxSuccessful() || authorResponse.getBody() == null) {
            return ResponseEntity.badRequest().build(); // HTTP 400, jeśli autor nie istnieje
        }

        // Pobierz dane autora z odpowiedzi
        AuthorDTO author = authorResponse.getBody();

        // Zaktualizuj istniejącą książkę
        Optional<Book> updatedBook = bookService.findBookById(id)
                .map(book -> {
                    book.setTitle(bookUpdateDTO.getTitle());
                    book.setPages(bookUpdateDTO.getPages());
                    book.setAuthorId(author.getUuid());
                    book.setAuthorName(author.getName());
                    return bookService.saveBook(book);
                });

        return updatedBook.map(book -> ResponseEntity.ok(new BookReadDTO(
                book.getUuid(),
                book.getTitle(),
                book.getPages(),
                book.getAuthorId(),
                book.getAuthorName()
        ))).orElse(ResponseEntity.notFound().build()); // HTTP 404 jeśli nie znaleziono książki
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build(); // HTTP 204 on success
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 if author doesn't exist
        }
    }

    @DeleteMapping("/deleteByAuthor/{authorId}")
    public ResponseEntity<Void> deleteBooksByAuthor(@PathVariable UUID authorId) {
        bookService.deleteBooksByAuthor(authorId);  // Usuwanie książek w serwisie
        return ResponseEntity.noContent().build();   // HTTP 204 No Content on success
    }
}