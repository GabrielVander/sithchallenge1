package com.sithchallenge.demo.controller;

import ch.rasc.sbjooqflyway.db.tables.daos.BookDao;
import ch.rasc.sbjooqflyway.db.tables.pojos.Book;
import com.sithchallenge.demo.exception.ResourceDuplicateException;
import com.sithchallenge.demo.exception.ResourceNotFoundException;
import org.jooq.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BookController {
    private final String endpoint = "/books";
    private final Logger logger = Logger.getLogger(BookController.class.getName());
    private final BookDao bookDao;

    public BookController(Configuration jooqConfiguration) {
        this.bookDao = new BookDao(jooqConfiguration);
    }

    @GetMapping(endpoint)
    public List<Book> allBooks(){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        return this.bookDao.findAll();
    }

    @PostMapping(endpoint)
    public Book newBook(@RequestBody Book newBook){
        logger.log(Level.INFO, "POST request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        if(this.bookDao.existsById(newBook.getId())){
            throw new ResourceDuplicateException("Resource with id " + newBook.getId() + "is already on database");
        }

        this.bookDao.insert(newBook);
        return newBook;
    }

    @GetMapping(endpoint + "/{id}")
    public Book getBook(@PathVariable int id){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        Book author = this.bookDao.fetchOneById(id);

        if(author == null){
            throw new ResourceNotFoundException("Couldn't find resource with id " + id);
        }

        return this.bookDao.fetchOneById(id);
    }

    @PutMapping(endpoint + "/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable int id){
        logger.log(Level.INFO, "PUT request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);
        newBook.setId(id);

        this.bookDao.update(newBook);
        return newBook;
    }

    @DeleteMapping(endpoint + "/{id}")
    public void deleteBook(@PathVariable int id){
        logger.log(Level.INFO, "DELETE request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        this.bookDao.deleteById(id);
    }

    private void logAction(String actionName){
        logger.log(Level.INFO, "action: '" + actionName + "'");
    }
}
