package com.sithchallenge.demo.DAO;

import ch.rasc.sbjooqflyway.db.tables.daos.AuthorDao;
import ch.rasc.sbjooqflyway.db.tables.daos.BookAuthorDao;
import com.sithchallenge.demo.model.Author;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class AuthorDAO{
    private AuthorDao authorDao;
    private BookAuthorDao bookAuthorDao;

    @Autowired
    public AuthorDAO(Configuration jooqConfiguration) {
        this.authorDao = new AuthorDao(jooqConfiguration);
        this.bookAuthorDao = new BookAuthorDao(jooqConfiguration);
    }

    public List<Author> getAll(){
        List<Author> authors = authorDao.findAll().stream().map(a -> new Author(a)).collect(Collectors.toList());
        for (Author author : authors) {
            author.setBooks(bookAuthorDao.fetchByAuthorid(author.getId()));
        }
        return authors;
    }
}
