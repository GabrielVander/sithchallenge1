package com.sithchallenge.demo.controller;

import com.sithchallenge.demo.DAO.AuthorDAO;
import com.sithchallenge.demo.model.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AuthorController {

    final String endpoint = "/authors";
    final Logger logger = Logger.getLogger(AuthorController.class.getName());

    @GetMapping(endpoint)
    List<Author> allAuthors(){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        return new AuthorDAO().getAll();
    }

    @PostMapping(endpoint)
    void newAuthor(@RequestBody Author author){
        logger.log(Level.INFO, "POST request - endpoint: '" + endpoint + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        new AuthorDAO().addSingle(author);
    }

    @GetMapping(endpoint + "/{id}")
    Author getAuthor(@PathVariable long id){
        logger.log(Level.INFO, "GET request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        return new AuthorDAO().getSingle(id);
    }

    @PutMapping(endpoint + "/{id}")
    void updateAuthor(@RequestBody Author newAuthor, @PathVariable long id){
        logger.log(Level.INFO, "PUT request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        new AuthorDAO().updateSingle(newAuthor, id);
    }

    @DeleteMapping(endpoint + "/{id}")
    void deleteAuthor(@PathVariable long id){
        logger.log(Level.INFO, "DELETE request - endpoint: '" + endpoint + "/" + id + "'");

        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        logAction(methodName);

        new AuthorDAO().deleteSingle(id);
    }

    private void logAction(String actionName){
        logger.log(Level.INFO, "action: '" + actionName + "'");
    }
}
