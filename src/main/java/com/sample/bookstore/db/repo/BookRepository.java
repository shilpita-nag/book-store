package com.sample.bookstore.db.repo;

import org.springframework.data.repository.CrudRepository;

import com.sample.bookstore.db.model.Books;
import java.util.List;

public interface BookRepository extends CrudRepository<Books, Long> {
    List<Books> findByTitle(String title);
}
