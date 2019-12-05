package com.sithchallenge.demo.model;

import ch.rasc.sbjooqflyway.db.tables.daos.AuthorDao;
import ch.rasc.sbjooqflyway.db.tables.daos.BookAuthorDao;
import ch.rasc.sbjooqflyway.db.tables.daos.BookDao;
import org.jooq.Configuration;


public class BookAuthor {
    private BookAuthorDao bookAuthorDao;
    private AuthorDao authorDao;
    private BookDao bookDao;

    public BookAuthor(Configuration jooqConfiguration) {
        this.bookAuthorDao = new BookAuthorDao(jooqConfiguration);
        this.authorDao = new AuthorDao(jooqConfiguration);
        this.bookDao = new BookDao(jooqConfiguration);
    }

    public Author setUpBookList(ch.rasc.sbjooqflyway.db.tables.pojos.Author author){
        Author author1 = new Author(author);

        author1.setBooks(bookAuthorDao.fetchByAuthorid(author.getId()));

        return author1;
    }
}
