package com.sample.bookstore.db.repo;

import com.sample.bookstore.db.model.Author;
import com.sample.bookstore.db.model.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    //List<Books> findAllBooksByAuthor(String author_name);
    List<Author> findByAuthorName(String authorName);
}
