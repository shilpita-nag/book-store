package com.sample.bookstore.controller;

import com.sample.bookstore.db.model.Books;
import com.sample.bookstore.db.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public Iterable findAll() {
        return bookRepository.findAll();
    }
}
