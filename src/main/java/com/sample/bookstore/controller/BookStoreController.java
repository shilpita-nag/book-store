package com.sample.bookstore.controller;

import com.sample.bookstore.db.model.Books;
import com.sample.bookstore.db.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{title}")
    public List findByTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Optional<Books> findById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Books create(@RequestBody Books book) {
        return bookRepository.save(book);
    }
}
