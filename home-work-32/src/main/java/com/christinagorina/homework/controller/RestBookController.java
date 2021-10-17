package com.christinagorina.homework.controller;

import com.christinagorina.homework.service.BookService;
import com.christinagorina.homework.to.BookTo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestBookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookTo> listPage() {

        return bookService.getAll();
    }

}
