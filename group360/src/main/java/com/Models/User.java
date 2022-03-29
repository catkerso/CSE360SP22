package com.Models;

public class User {
    private String email;
    private String fname; 
    private String lname;
    private boolean manager;
    
    public User() {}

    public User(String email, String fname, String lname, boolean manager) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.manager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public String getFullName() {
        return fname + " " + lname;
    }
    

    public boolean isManager() {
        return manager;
    }

    public String toString() {
       return "Email: " + email + "\nName:" + fname + " " + lname; 
    }

}
