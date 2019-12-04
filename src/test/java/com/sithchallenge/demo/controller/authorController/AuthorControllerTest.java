package com.sithchallenge.demo.controller.authorController;

import ch.rasc.sbjooqflyway.db.tables.daos.AuthorDao;
import ch.rasc.sbjooqflyway.db.tables.pojos.Author;
import com.sithchallenge.demo.controller.AbstractTest;
import org.jooq.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AuthorControllerTest extends AbstractTest {
    @Autowired
    private Configuration jooqConfiguration;
    private AuthorDao authorDao;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        this.authorDao = new AuthorDao(jooqConfiguration);
    }

    @Test
    public void getAuthorsList() throws Exception {
        String uri = "/authors";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Author[] authorList = super.mapFromJson(content, Author[].class);
    }

    @Test
    public void getAuthor() throws Exception {
        String uri = "/authors/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getWrongAuthor() throws Exception {
        String uri = "/authors/1111111";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void createAuthor() throws Exception {
        String uri = "/authors";
        Author author = new Author(99, "Jack", "O' Lantern", LocalDate.now(), true);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void createWrongAuthor() throws Exception {
        String uri = "/authors";
        Author author = new Author(1, "Jack", "O' Lantern", LocalDate.now(), true);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);
    }

    @Test
    public void updateAuthor() throws Exception {
        String uri = "/authors/1";
        Author author = this.authorDao.fetchOneById(1);
        author.setDistinguished(true);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateWrongAuthor() throws Exception {
        String uri = "/authors/111111";
        Author author = this.authorDao.fetchOneById(1);
        author.setDistinguished(false);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Author is updated successfully");
    }

    @Test
    public void deleteAuthor() throws Exception {
        String uri = "/authors/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Author is deleted successfully");
    }

    @Test
    public void deleteWrongAuthor() throws Exception {
        String uri = "/authors/111111111";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Author is deleted successfully");
    }
}
