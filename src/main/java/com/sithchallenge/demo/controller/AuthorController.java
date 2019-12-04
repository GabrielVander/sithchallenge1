package com.sithchallenge.demo.controller;

import ch.rasc.sbjooqflyway.db.tables.daos.AuthorDao;
import ch.rasc.sbjooqflyway.db.tables.pojos.Author;
import com.sithchallenge.demo.exception.ResourceNotFoundException;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AuthorController {

    private final String endpoint = "/authors";
    private final Logger logger = Logger.getLogger(AuthorController.class.getName());
    private final AuthorDao authorDao;
    private final DSLContext dsl;

    public AuthorController(DSLContext dsl, Configuration jooqConfiguration) {
        this.authorDao = new AuthorDao(jooqConfiguration);
        this.dsl = dsl;
    }

    @GetMapping(endpoint)
    public List<Author> allAuthors(){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        return this.authorDao.findAll();
    }

    @PostMapping(endpoint)
    public Author newAuthor(@RequestBody Author newAuthor){
        logger.log(Level.INFO, "POST request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        if(this.authorDao.existsById(newAuthor.getId())){

        }

        this.authorDao.insert(newAuthor);
        return newAuthor;
    }

    @GetMapping(endpoint + "/{id}")
    public Author getAuthor(@PathVariable int id){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        Author author = this.authorDao.fetchOneById(id);

        if(author == null){
            throw new ResourceNotFoundException("Couldn't find resource with id " + id);
        }

        return this.authorDao.fetchOneById(id);
    }

    @PutMapping(endpoint + "/{id}")
    public Author updateAuthor(@RequestBody Author newAuthor, @PathVariable int id){
        logger.log(Level.INFO, "PUT request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);
        newAuthor.setId(id);

        this.authorDao.update(newAuthor);
        return newAuthor;
    }

    @DeleteMapping(endpoint + "/{id}")
    public void deleteAuthor(@PathVariable int id){
        logger.log(Level.INFO, "DELETE request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        this.authorDao.deleteById(id);
    }

    private void logAction(String actionName){
        logger.log(Level.INFO, "action: '" + actionName + "'");
    }
}
