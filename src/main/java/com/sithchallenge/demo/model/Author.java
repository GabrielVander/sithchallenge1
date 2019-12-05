package com.sithchallenge.demo.model;

import ch.rasc.sbjooqflyway.db.tables.pojos.BookAuthor;
import ch.rasc.sbjooqflyway.db.tables.records.AuthorRecord;

import java.util.ArrayList;
import java.util.List;

public class Author extends AuthorRecord {
    private List<Book> books = new ArrayList<>();

    public Author(AuthorRecord record) {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
