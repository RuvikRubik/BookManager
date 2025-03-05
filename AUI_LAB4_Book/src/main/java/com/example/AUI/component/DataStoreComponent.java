package com.example.AUI.component;

import com.example.AUI.DTO.AuthorDTO;
import com.example.AUI.entity.Book;
import com.example.AUI.service.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataStoreComponent implements CommandLineRunner {


    private final BookService bookService;
    private final RestTemplate restTemplate;

    @Autowired
    public DataStoreComponent(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        final String AUTHOR_SERVICE_URL = "http://localhost:8082/author";

        // Tworzenie nowego autora przez mikroserwis zarządzający autorami
        AuthorDTO newAuthor = new AuthorDTO("Jan Kowalski", 30);
        try {
            ResponseEntity<AuthorDTO> authorResponse = restTemplate.postForEntity(AUTHOR_SERVICE_URL, newAuthor, AuthorDTO.class);

            if (authorResponse.getStatusCode().is2xxSuccessful() && authorResponse.getBody() != null) {
                // Pobierz UUID zapisanego autora
                AuthorDTO savedAuthor = authorResponse.getBody();

                // Utwórz książki i przypisz do autora
                Book book = new Book("Pierwsza książka", 300);
                book.setAuthorId(savedAuthor.getUuid());
                book.setAuthorName(savedAuthor.getName()); // Opcjonalnie zapisz nazwę autora
                bookService.saveBook(book);

                Book book2 = new Book("Druga", 500);
                book2.setAuthorId(savedAuthor.getUuid());
                book2.setAuthorName(savedAuthor.getName());
                bookService.saveBook(book2);

            } else {
                throw new RuntimeException("Nie udało się utworzyć autora w Author Service. Odpowiedź: " + authorResponse.getStatusCode());
            }
        } catch (Exception e) {
            // W przypadku błędów podczas komunikacji z serwisem Author
            throw new RuntimeException("Błąd podczas komunikacji z mikroserwisem Author Service: " + e.getMessage());
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }

    /*
        private void listAllAuthors() {
        System.out.println("Listing all authors:");
        authorService.findAllAuthors().forEach(author ->
                System.out.println(author.getUuid() + " - " + author.getName() + " (age: " + author.getAge() + ")"));
    }

    private void listAllBooks() {
        System.out.println("Listing all books:");
        bookService.findAllBooks().forEach(book ->
                System.out.println(book.getUuid() + " - " + book.getTitle() + " (pages: " + book.getPages() + ", author: " + book.getAuthor().getName() + ")"));
    }

    private void addNewAuthor(Scanner scanner) {
        System.out.println("Adding a new author:");
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();

        System.out.print("Enter author age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Author author = new Author(UUID.randomUUID(), name, age);
        authorService.saveAuthor(author);

        System.out.println("Author added successfully!");
    }

    private void addNewBook(Scanner scanner) {
        System.out.println("Adding a new book:");

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter number of pages: ");
        int pages = Integer.parseInt(scanner.nextLine());

        System.out.println("Select an author by UUID:");
        listAllAuthors();

        System.out.print("Enter author UUID: ");
        UUID authorId = UUID.fromString(scanner.nextLine());

        Author author = authorService.findAuthorById(authorId).orElseThrow(() -> new RuntimeException("Author not found!"));
        Book book = new Book(UUID.randomUUID(), title, pages);
        book.setAuthor(author);
        bookService.saveBook(book);

        System.out.println("Book added successfully!");
    }

    private void deleteBookByUUID(Scanner scanner) {
        System.out.print("Enter the UUID of the book to delete: ");
        UUID bookId = UUID.fromString(scanner.nextLine());

        bookService.deleteBook(bookId);
        System.out.println("Book deleted successfully!");
    }


     */
}