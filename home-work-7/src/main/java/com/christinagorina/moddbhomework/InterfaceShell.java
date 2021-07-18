package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.service.BookService;
import com.christinagorina.moddbhomework.to.BookTo;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class InterfaceShell {
    private final BookService bookService;

    @ShellMethod(key = "update_book", value = "Update Book")
    public BookTo update(@ShellOption({"bookName", "n"}) String bookName,
                       @ShellOption({"authorId", "a"}) String author,
                       @ShellOption({"genreId", "g"}) String genre) {
        BookTo updatedBook = new BookTo(1L, bookName, author, genre);
        return bookService.update(updatedBook);
    }

    @ShellMethod(key = "create_book", value = "Create Book")
    public BookTo create(@ShellOption({"bookName", "n"}) String bookName,
                       @ShellOption({"author", "a"}) String author,
                       @ShellOption({"genre", "g"}) String genre) {
        BookTo createdBookTo = new BookTo(null, bookName, author, genre);
        return bookService.create(createdBookTo);
    }

    @ShellMethod(key = "delete_book", value = "Deleted Book")
    public boolean delete(){
        return bookService.delete(3L);
    }

    @ShellMethod(key = "getById_book", value = "GetById Book")
    public BookTo getById(@ShellOption({"id", "a"}) Long id){
        return bookService.getById(id);
    }

    @ShellMethod(key = "getAll_book", value = "GetAll Book")
    public List<BookTo> getAll(){
        return bookService.getAll();
    }


}
