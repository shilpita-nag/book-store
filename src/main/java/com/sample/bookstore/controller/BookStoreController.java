package com.sample.bookstore.controller;

import com.sample.bookstore.Exception.BookNotFoundException;
import com.sample.bookstore.db.model.Author;
import com.sample.bookstore.db.model.Books;
import com.sample.bookstore.db.repo.AuthorRepository;
import com.sample.bookstore.db.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{title}")
    public List findByTitle(@PathVariable String title) {
        List<Books> booksList = bookRepository.findByTitle(title);
        if(booksList.isEmpty()) {
            throw new BookNotFoundException("No Book found with title : " + title);
        }
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Optional<Books> findById(@PathVariable Long id) {
        Optional<Books> booksOptional = bookRepository.findById(id);
        if(booksOptional.isEmpty()) {
            throw new BookNotFoundException("No Book found with id : " + id);
        }
        return bookRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody Books book) {
        Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getAuthorId());
        if(!optionalAuthor.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        book.setAuthor(optionalAuthor.get());

        Books saveBook = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveBook.getBook_id()).toUri();
        return ResponseEntity.created(location).body(saveBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBook(@RequestBody Books book, @PathVariable Long id) throws Exception {
        Optional<Books> booksOptional = bookRepository.findById(id);
        if(booksOptional.isEmpty()) {
            throw new BookNotFoundException("No Book found with id : " + id);
        }
        book.setBook_id(id);
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }
}
