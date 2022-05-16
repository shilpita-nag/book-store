package com.sample.bookstore.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long book_id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="isbn",nullable = false)
    private String isbn;

    @Column(name="publisher")
    private String publisher;

    @Column(name = "inventory",nullable = false)
    private int inventory;

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }

    @ElementCollection
    @CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "genre")
    private Set<String> genre = new HashSet<>();

    public Books() {
    }

    public Books(String title, String isbn, String publisher, int inventory) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.inventory = inventory;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

}
