package com.example.AUI.controller;

import com.example.AUI.DTO.AuthorCreateUpdateDTO;
import com.example.AUI.DTO.AuthorListDTO;
import com.example.AUI.DTO.AuthorReadDTO;
import com.example.AUI.DTO.BookReadDTO;
import com.example.AUI.entity.Author;
import com.example.AUI.service.AuthorService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    private final RestTemplate restTemplate;

    public AuthorController(AuthorService authorService, RestTemplate restTemplate) {
        this.authorService = authorService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<AuthorListDTO> listAuthors() {
        return authorService.findAllAuthors().stream()
                .map(author -> new AuthorListDTO(author.getUuid(), author.getName()))
                .collect(Collectors.toList()); // Returns a list of AuthorListDTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> getAuthor(@PathVariable UUID id) {
        Optional<Author> author = authorService.findAuthorById(id);

        if (author.isPresent()) {

            String booksServiceUrl = "http://localhost:8081/book/author/" + id;

            try {
                ResponseEntity<List<BookReadDTO>> booksResponse = restTemplate.exchange(booksServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookReadDTO>>() {});

                List<BookReadDTO> books = booksResponse.getBody();

                AuthorReadDTO authorReadDTO = new AuthorReadDTO(
                        author.get().getUuid(),
                        author.get().getName(),
                        author.get().getAge(),
                        books
                );

                return ResponseEntity.ok(authorReadDTO);
            } catch (RestClientException e) {
                // Obsługuje wyjątek w przypadku błędu połączenia
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.notFound().build(); // HTTP 404 if not found
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthorReadDTO> updateAuthor(@RequestBody AuthorCreateUpdateDTO authorUpdateDTO, @PathVariable UUID id) {
        Optional<Author> updatedAuthor = authorService.findAuthorById(id)
                .map(author -> {
                    author.setName(authorUpdateDTO.getName());
                    author.setAge(authorUpdateDTO.getAge());
                    return authorService.saveAuthor(author);
                });

        return updatedAuthor.map(author -> ResponseEntity.ok(new AuthorReadDTO(author.getUuid(), author.getName(), author.getAge())))
                .orElse(ResponseEntity.notFound().build()); // HTTP 404 if not found
    }


    @PostMapping
    public ResponseEntity<AuthorReadDTO> addAuthor(@RequestBody AuthorCreateUpdateDTO authorCreateDTO) {
        Author newAuthor = new Author();
        newAuthor.setName(authorCreateDTO.getName());
        newAuthor.setAge(authorCreateDTO.getAge());
        Author savedAuthor = authorService.saveAuthor(newAuthor);

        return ResponseEntity.ok(new AuthorReadDTO(savedAuthor.getUuid(), savedAuthor.getName(), savedAuthor.getAge()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        // Najpierw usuwamy książki powiązane z autorem
        String bookServiceUrl = "http://localhost:8081/book/deleteByAuthor/" + id;
        try {
            // Wysyłamy żądanie do serwisu książek, aby usunąć wszystkie książki powiązane z tym autorem
            restTemplate.delete(bookServiceUrl);
        } catch (Exception e) {
            // Jeśli coś pójdzie nie tak, zwróćmy błąd 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Teraz usuwamy autora
        if (authorService.deleteAuthor(id)) {
            return ResponseEntity.noContent().build(); // HTTP 204 on success
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 if author doesn't exist
        }
    }



}
