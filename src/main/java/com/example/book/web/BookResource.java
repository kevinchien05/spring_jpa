package com.example.book.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.BookCreateRequestDTO;
import com.example.book.dto.BookDetailResponseDTO;
import com.example.book.dto.BookListResponseDTO;
import com.example.book.dto.BookUpdateRequestDTO;
import com.example.book.service.BookService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class BookResource {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") Long bookId) {
        BookDetailResponseDTO dto = bookService.findBookDetail(bookId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/book")
    public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDTO dto) throws URISyntaxException {
        bookService.createNewBook(dto);
        return ResponseEntity.created(new URI("/book")).build();
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookListResponseDTO>> findBookAll(@RequestParam(value = "title", required = false) String title) {
        List<BookListResponseDTO> dtos = bookService.findAllBook(title);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateRequestDTO dto) {
        bookService.updateBook(bookId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}
