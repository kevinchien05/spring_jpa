package com.example.book.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.book.domain.Book;
import com.example.book.dto.BookCreateRequestDTO;
import com.example.book.dto.BookDetailResponseDTO;
import com.example.book.dto.BookListResponseDTO;
import com.example.book.dto.BookUpdateRequestDTO;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    // public BookServiceImpl() {
    //     super();
    //     Book book1 = new Book();
    //     book1.setId("1");
    //     book1.setName("AC");
    //     book1.setAuthor("Budi");
    //     book1.setDescription("Testing");
    //     Book book2 = new Book();
    //     book2.setId("2");
    //     book2.setName("AC2");
    //     book2.setAuthor("Budi2");
    //     book2.setDescription("Testing2");
    //     books.put(book1.getId(), book1);
    //     books.put(book2.getId(), book2);
    // }

    @Override
    public List<BookListResponseDTO> findAllBook(String title) {

        title = ObjectUtils.isEmpty(title)?"%":"%"+title+"%";

        List<Book> bookResponses = bookRepository.findAllByTitleLike(title);
        return bookResponses.stream().map((b) -> {
            BookListResponseDTO dto = new BookListResponseDTO();
            dto.setTitle(b.getTitle());
            dto.setAuthor(b.getAuthor());
            dto.setDescription(b.getDescription());
            dto.setId(b.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createNewBook(BookCreateRequestDTO dto) {
        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDTO findBookDetail(Long bookId) {
        Book book =  bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found"));
        BookDetailResponseDTO dto = new BookDetailResponseDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setDescirption(book.getDescription());
        return dto;
    }

    @Override
    public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
        Book book =  bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found"));
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
