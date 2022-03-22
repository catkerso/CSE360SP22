package com.Helpers;

import com.Models.User;

import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class Auth {

    private static boolean isAuthed = false;
    private static User authedUser = null;

    /**
     * Returns whether the client is authed or not
     * 
     * @return authentication status
     */
    public static boolean isSignedIn() {

        return isAuthed;
    }

    /**
     * 
     * @param email
     * @param pass
     * @return User object of the user signed in.  Will return null if login was unsucessful.
     */
    public static User signIn(String email, String pass) {
        if (!doesUserExist(email))
            return null;
        JSONObject u = (JSONObject) DB.db.getUserAuthObject().get(email);
        u.get("password");
        if (BCrypt.checkpw(pass, (String) u.get("password"))) {
            JSONObject user = (JSONObject) DB.db.getUserObject().get(String.valueOf((long) u.get("userID")));
            String u_email = (String) user.get("email");
            String fname = (String) user.get("fname");
            String lname = (String) user.get("lname");
        
            User r_user = new User();
            r_user.setEmail(u_email);
            r_user.setFname(fname);
            r_user.setLname(lname);
            
            isAuthed = true;
            return r_user;
        } 
        return null;
    }

    /**
     * Attempt to sign up a user
     * @param email    email of the user
     * @param password password of the user, in plaintext
     * @param fname    first name
     * @param lname    last name
     * @return sign up success
     */
    public static boolean signUp(String email, String pass, String fname, String lname) {
        if (doesUserExist(email))
            return false;
        String h_pass = BCrypt.hashpw(pass, BCrypt.gensalt());
        DB.db.addUser(email, h_pass, fname, lname);
        return false;
    }

    /**
     * Returns whether or not a user exists in the loaded database by email.
     * @param email
     * @return whether user exists or not
     */
    public static boolean doesUserExist(String email) {
        return DB.db.getUserAuthObject().get(email) != null;
    }

    /** 
     * Returns the authenticated user for the current client.  will return null if there is no current user signed in.
     * @return current authed user
     */        
    public static User getCurrentUser() {
        return authedUser;
    }

}
