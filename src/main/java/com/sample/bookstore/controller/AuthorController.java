package com.sample.bookstore.controller;

import com.sample.bookstore.db.model.Author;
import com.sample.bookstore.db.model.Books;
import com.sample.bookstore.db.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public Iterable findAllAuthor() {
        return authorRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Books> findAllBooksByAuthor(@PathVariable  String name) {
        List<Author> authorList = authorRepository.findByAuthorName(name);
        /*if(authorList.isEmpty()) {
            throw new AuthorNotFoundException("name: "+name);
        }*/
        return authorList.get(0).getBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createAuthor(@RequestBody Author author) {
        Optional<Author> authorOptional = authorRepository.findById(author.getAuthorId());
        if(authorOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Author saveAuthor = authorRepository.save(author);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveAuthor.getAuthorId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAuthor(@RequestBody Author author, @PathVariable Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        author.setAuthorId(id);
        authorRepository.save(author);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}
