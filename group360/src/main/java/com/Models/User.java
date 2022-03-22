package com.Models;

public class User {
    public String email;
    public String fname; 
    public String lname;

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
    


    public String toString() {
       return "Email: " + email + "\nName:" + fname + " " + lname; 
    }

}
