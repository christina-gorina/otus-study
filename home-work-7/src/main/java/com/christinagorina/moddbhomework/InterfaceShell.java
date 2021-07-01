package com.christinagorina.moddbhomework;

import com.christinagorina.moddbhomework.domain.Book;
import com.christinagorina.moddbhomework.service.BookService;
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
    public Book update(@ShellOption({"bookName", "n"}) String bookName,
                       @ShellOption({"authorId", "a"}) Long authorId,
                       @ShellOption({"genreId", "g"}) Long genreId) {
        Book updatedBook = new Book(1L, bookName, authorId, genreId);
        return bookService.update(updatedBook);
    }

    @ShellMethod(key = "create_book", value = "Create Book")
    public Book create(@ShellOption({"bookName", "n"}) String bookName,
                       @ShellOption({"authorId", "a"}) Long authorId,
                       @ShellOption({"genreId", "g"}) Long genreId) {
        Book createdBook = new Book(null, bookName, authorId, genreId);
        return bookService.update(createdBook);
    }

    @ShellMethod(key = "delete_book", value = "Deleted Book")
    public boolean delete(){
        return bookService.delete(3L);
    }

    @ShellMethod(key = "getById_book", value = "GetById Book")
    public Book getById(@ShellOption({"id", "a"}) Long id){
        return bookService.getById(id);
    }

    @ShellMethod(key = "getAll_book", value = "GetAll Book")
    public List<Book> getAll(){
        return bookService.getAll();
    }


}
