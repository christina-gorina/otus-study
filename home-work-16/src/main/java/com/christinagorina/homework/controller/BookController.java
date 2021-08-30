package com.christinagorina.homework.controller;

import com.christinagorina.homework.domain.Genre;
import com.christinagorina.homework.service.BookService;
import com.christinagorina.homework.service.GenreService;
import com.christinagorina.homework.to.BookTo;
import com.christinagorina.homework.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<BookTo> books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookpage";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        BookTo book = bookService.getById(id);
        List<Genre> genres = genreService.findAll();

        if (Objects.isNull(book)) {
            throw new NotFoundException("Book not found");
        }
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        return "editbook";
    }

    @PostMapping("/edit")
    public String edit(BookTo book, Model model) {
        BookTo bookSaved;
        if(Objects.isNull(book.getId())){
            bookSaved = bookService.create(book);
        }else{
            bookSaved = bookService.update(book);
        }

        model.addAttribute(bookSaved);
        return "redirect:/edit/?id=" + bookSaved.getId();
    }

    @GetMapping("/create")
    public String create(Model model) {
        BookTo book = new BookTo();
        model.addAttribute("book", book);
        return "editbook";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam("id") int id) {
        bookService.delete(id);
        return "redirect:/";
    }

}
