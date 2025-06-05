package com.example.book.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.book.domain.Author;
import com.example.book.domain.Book;
import com.example.book.dto.BookCreateRequestDTO;
import com.example.book.dto.BookDetailResponseDTO;
import com.example.book.dto.BookListResponseDTO;
import com.example.book.dto.BookUpdateRequestDTO;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<BookListResponseDTO> findAllBook(String title) {

        title = ObjectUtils.isEmpty(title)?"%":"%"+title+"%";

        List<Book> bookResponses = bookRepository.findAllByTitleLike(title);
        return bookResponses.stream().map((b) -> {
            BookListResponseDTO dto = new BookListResponseDTO();
            dto.setTitle(b.getTitle());
            dto.setAuthor(b.getAuthor().getName());
            dto.setAuthorId(b.getAuthor().getId());
            dto.setDescription(b.getDescription());
            dto.setId(b.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewBook(BookCreateRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("invalid author_id"));
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDTO findBookDetail(Long bookId) {
        Book book =  bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found"));
        BookDetailResponseDTO dto = new BookDetailResponseDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setAuthor(book.getAuthor().getName());
        dto.setDescirption(book.getDescription());
        return dto;
    }

    @Override
    public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("invalid author_id"));
        Book book =  bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found"));
        book.setAuthor(author);
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
