package com.primebank.webservices.books;

import com.exceptionhandler.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<Book> getBookList() {
        return new ArrayList<>();//Arrays.asList(new Book(1l,"Ramayon","Balmiki"));
    }

    @PostMapping(path = "/add-book")
    public List<Book> addBook(@RequestBody @Valid Book book) {
        return bookService.addBook(book);
    }
}
