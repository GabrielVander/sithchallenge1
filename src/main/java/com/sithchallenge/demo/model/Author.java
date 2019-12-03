package com.sithchallenge.demo.model;

import java.util.Date;

public class Author {
    private long id;
    private  String firstName;
    private  String lastName;
    private Date birthday;
    private boolean distinguished;

    public Author(){
    }

    public Author(String firstName, String lastName, Date birthday, boolean distinguished) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.distinguished = distinguished;
    }

    public Author(long id, String firstName, String lastName, Date birthday, boolean distinguished) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.distinguished = distinguished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isDistinguished() {
        return distinguished;
    }

    public void setDistinguished(boolean distinguished) {
        this.distinguished = distinguished;
    }
}
