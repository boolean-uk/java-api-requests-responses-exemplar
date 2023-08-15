package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Books {
    private List<Book> books;

    public Books() {
        this.books = new ArrayList<>();
        Book lordRings = new Book(0, "The Lord of the Rings", 1150, "JRR Tolkien", "Fantasy");
        this.books.add(lordRings);
        Book twoCities = new Book(this.books.size(), "A Tale of Two Cities", 756, "Charles Dickens", "Historical");
        this.books.add(twoCities);
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        int bookToAddId = this.books.size();
        book.setId(bookToAddId);
        this.books.add(book);
        return this.books.get(bookToAddId);
    }

    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable int id) {
        Book book = null;
        if (id < this.books.size()) {
            book = this.books.get(id);
        }
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Book bookToUpdate = null;
        if (id < this.books.size()) {
            book.setId(id);
            this.books.set(id, book);
            bookToUpdate = this.books.get(id);
        }
        return bookToUpdate;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        Book bookToDelete = null;
        if (id < this.books.size()) {
            bookToDelete = this.books.get(id);
            this.books.remove(id);
        }
        // Fix each book's id so it matches the book's position in the ArrayList
        for (int i = 0; i < this.books.size(); i++) {
            Book book = this.books.get(i);
            book.setId(i);
            this.books.set(i, book);
        }
        return bookToDelete;
    }
}
