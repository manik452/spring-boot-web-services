package com.primebank.webservices.books;


import javax.validation.constraints.Size;

public class Book {
    private long id;
    private String author;

    @Size(min=4,max = 4, message = "Book name must be greater than 4 character")
    private String name;

    public Book(long id, String author, String name) {
        this.id = id;
        this.author = author;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
