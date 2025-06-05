package com.example.book.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.AuthorBookResponseDTO;
import com.example.book.dto.AuthorCreateReqDTO;
import com.example.book.service.AuthorService;

@RestController
public class AuthorResource {

    @Autowired
    public AuthorService authorService;

    @PostMapping(value="/author")
    public ResponseEntity<Void> createNewAuthor(@RequestBody AuthorCreateReqDTO dto) throws URISyntaxException{
        authorService.createNewAuthor(dto);
        return ResponseEntity.created(new URI("/v1/author")).build();
    }

    @GetMapping(value="/author/{authorId}/book")
    public ResponseEntity<List<AuthorBookResponseDTO>> findAuthorBook(@PathVariable("authorId") Long authorId){
        List<AuthorBookResponseDTO> result =  authorService.findAuthorBook(authorId);
        return ResponseEntity.ok().body(result);
    }
}
