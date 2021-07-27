package com.christinagorina.homework;

import com.christinagorina.homework.service.BookServiceImpl;
import com.christinagorina.homework.to.BookTo;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Collections;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class InterfaceShell {
    private final BookServiceImpl bookServiceImpl;

    @ShellMethod(key = "update_book", value = "Update Book")
    public BookTo update(@ShellOption({"bookName", "n"}) String bookName,
                         @ShellOption({"authorNames", "a"}) List<String> author,
                         @ShellOption({"genre", "g"}) String genre,
                         @ShellOption({"comment", "a"}) String comment) {
        List<String> comments = Collections.singletonList(comment);
        BookTo updatedBook = new BookTo(1L, bookName, author, genre, comments);
        return bookServiceImpl.update(updatedBook);
    }

    @ShellMethod(key = "create_book", value = "Create Book")
    public BookTo create(@ShellOption({"bookName", "n"}) String bookName,
                         @ShellOption({"author", "a"}) List<String> author,
                         @ShellOption({"genre", "g"}) String genre,
                         @ShellOption({"comment", "a"}) List<String> comment) {
        BookTo createdBookTo = new BookTo(null, bookName, author, genre, comment);
        return bookServiceImpl.create(createdBookTo);
    }

    @ShellMethod(key = "getById_book", value = "GetById Book")
    public BookTo getById(@ShellOption({"id", "a"}) Long id){
        return bookServiceImpl.getById(id);
    }

    @ShellMethod(key = "delete_book", value = "Deleted Book")
    public void delete(){
        bookServiceImpl.delete(3L);
    }

    @ShellMethod(key = "getAll_book", value = "GetAll Book")
    public List<BookTo> getAll(){
        return bookServiceImpl.getAll();
    }

}
