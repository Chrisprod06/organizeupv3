package com.example.organizeupv3.models;

import java.io.Serializable;

public class User implements Serializable {

    //Variable declaration
    private int userID;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    //Full constructor
    public void User(int uid, String em, String fn, String ln, String pw){
        setUserID(uid);
        setEmail(em);
        setFirstname(fn);
        setLastname(ln);
        setPassword(pw);
    }

    //Empty constructor
    public void User() {
        setUserID(0);
        setEmail("email");
        setFirstname("firstname");
        setLastname("lastname");
        setPassword("password");
    }
    //Get methods

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }


    //Set methods

    public void setUserID(int uid) {
        this.userID = uid;
    }

    public void setEmail(String em) {
        this.email = em;
    }

    public void setFirstname(String fn) {
        this.firstname = fn;
    }

    public void setLastname(String ln) {
        this.lastname = ln;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

}
