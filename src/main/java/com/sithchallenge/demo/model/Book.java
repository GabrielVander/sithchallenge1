package com.sithchallenge.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Book extends ch.rasc.sbjooqflyway.db.tables.pojos.Book {
    private List<Author> authors = new ArrayList<>();

    public Book(ch.rasc.sbjooqflyway.db.tables.pojos.Book value) {
        super(value);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
