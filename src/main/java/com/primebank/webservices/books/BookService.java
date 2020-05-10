package com.primebank.webservices.books;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookService {
    public static List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book(1,"Balmiki","Ramayon"));
        bookList.add(new Book(1,"Veda-Vyasa","Bhagavad Gita"));
    }

    public List<Book> addBook(Book book){
        bookList.add(book);
        return bookList;
    }

}
