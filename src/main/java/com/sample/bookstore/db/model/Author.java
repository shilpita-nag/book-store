package com.sample.bookstore.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorId;

    @Column(name = "name", nullable = false, unique = true)
    private String authorName;

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
        for(Books b:books) {
            b.setAuthor(this);
        }
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Books> books = new ArrayList<>();

    public Author() {
    }

    public Author(long authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthor_name(String author_name) {
        this.authorName = author_name;
    }
}
