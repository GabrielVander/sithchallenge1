package com.sithchallenge.demo.DAO;

import com.sithchallenge.demo.exception.ResourceNotFoundException;
import com.sithchallenge.demo.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private static List<Author> authors = new ArrayList<>();

    public List<Author> getAll(){
        return authors;
    }

    public Author getSingle(long id) throws ResourceNotFoundException {
        for (Author author : authors) {
            if (author.getId() == id) {
                return author;
            }
        }

        throw new ResourceNotFoundException("Unable to find a resource with the provided id");
    }

    public void addSingle(Author author){
        authors.add(author);
    }

    public void updateSingle(Author newAuthor, long id) throws ResourceNotFoundException{
        Author author = getSingle(id);
        author.setFirstName(newAuthor.getFirstName());
        author.setLastName(newAuthor.getLastName());
        author.setBirthday(newAuthor.getBirthday());
        author.setDistinguished(newAuthor.isDistinguished());
    }

    public void deleteSingle(long id){
        authors.remove(getSingle(id));
    }
}
