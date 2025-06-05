package com.example.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book.domain.Author;
import com.example.book.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //select * from book where title LIKE 'name'
    List<Book> findAllByTitleLike(String title);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByAuthor_Id(Long AuthorId);
}
