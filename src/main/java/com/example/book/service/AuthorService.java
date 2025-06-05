package com.example.book.service;

import java.util.List;

import com.example.book.dto.AuthorBookResponseDTO;
import com.example.book.dto.AuthorCreateReqDTO;

public interface AuthorService {
    public void createNewAuthor(AuthorCreateReqDTO dto);

    public List<AuthorBookResponseDTO> findAuthorBook(Long authorId);
}
