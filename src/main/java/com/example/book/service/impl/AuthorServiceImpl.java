package com.example.book.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.domain.Author;
import com.example.book.domain.Book;
import com.example.book.dto.AuthorBookResponseDTO;
import com.example.book.dto.AuthorCreateReqDTO;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import com.example.book.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createNewAuthor(AuthorCreateReqDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));
        authorRepository.save(author);
    }

    @Override
    public List<AuthorBookResponseDTO> findAuthorBook(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author_id invalid"));
        // List<Book> books = bookRepository.findAllByAuthor_Id(authorId);
        List<Book> books = author.getBooks();
        List<AuthorBookResponseDTO> dtos =  books.stream().map((b) -> {
            AuthorBookResponseDTO dto = new AuthorBookResponseDTO();
            dto.setBookName(b.getTitle());
            dto.setDescription(b.getDescription());
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

}
