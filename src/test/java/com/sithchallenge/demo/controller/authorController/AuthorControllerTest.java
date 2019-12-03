package com.sithchallenge.demo.controller.authorController;

import com.sithchallenge.demo.controller.AbstractTest;
import com.sithchallenge.demo.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class AuthorControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
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
        assertTrue(authorList.length > 0);
    }

    @Test
    public void createAuthor() throws Exception {
        String uri = "/authors";
        Author author = new Author(3L,"Jack", "O' Lantern", new Date(), true);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Author is created successfully");
    }

    @Test
    public void updateAuthor() throws Exception {
        String uri = "/authors/3";
        Author author = new Author(3L,"Jack", "O' Lantern", new Date(), false);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Author is updated successsfully");
    }

    @Test
    public void deleteAuthor() throws Exception {
        String uri = "/authors/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Author is deleted successsfully");
    }
}