package com.example.book.service;

import java.util.List;

import com.example.book.dto.BookCreateRequestDTO;
import com.example.book.dto.BookDetailResponseDTO;
import com.example.book.dto.BookListResponseDTO;
import com.example.book.dto.BookUpdateRequestDTO;

public interface BookService {
    public List<BookListResponseDTO> findAllBook(String title);

    public void createNewBook(BookCreateRequestDTO dto);

    public BookDetailResponseDTO findBookDetail(Long bookId);

    public void updateBook(Long bookId, BookUpdateRequestDTO dto);

    public void deleteBook(Long bookId);
}
