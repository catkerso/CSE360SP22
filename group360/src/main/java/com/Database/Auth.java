package com.Database;

import java.util.regex.Pattern;

import com.Objects.User;
import com.ViewControllers.App;

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
        JSONObject u = (JSONObject) App.db.getUserAuthObject().get(email);
        u.get("password");
        if (BCrypt.checkpw(pass, (String) u.get("password"))) {
            String uid = String.valueOf(u.get("userID"));
            JSONObject user = (JSONObject) App.db.getUserObject().get(uid);
            String u_email = (String) user.get("email");
            String fname = (String) user.get("fname");
            String lname = (String) user.get("lname");
            boolean man = Integer.parseInt(String.valueOf(user.get("manager"))) == 1;
        
            User r_user = new User(Integer.parseInt(uid), u_email, fname, lname, man);
            
            isAuthed = true;
            authedUser = r_user;
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
        App.db.addUser(email, h_pass, fname, lname);
        return false;
    }

    /**
     * Returns whether or not a user exists in the loaded database by email.
     * @param email
     * @return whether user exists or not
     */
    public static boolean doesUserExist(String email) {
        return App.db.getUserAuthObject().get(email) != null;
    }


    /**
     * Returns the user with the given id
     * @return User object of the user with the id given. Will return null if no user is found.
     */
    public static boolean doesUserExist(int id) {
        return App.db.getUserObject().get(String.valueOf(id)) != null;
    }


    /** 
     * Returns the authenticated user for the current client.  will return null if there is no current user signed in.
     * @return current authed user
     */        
    public static User getCurrentUser() {
        return authedUser;
    }


    /**
     * Checks whether or not a given email adress is formatted as an email or not
     * @param email input
     * @return validity of email
     */
    public static boolean isValidEmailAddress(String email) {
        return Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches();
    }



    public static User getUserById(int id) {
        JSONObject user = (JSONObject) App.db.getUserObject().get(String.valueOf(id));
        String u_email = (String) user.get("email");
        String fname = (String) user.get("fname");
        String lname = (String) user.get("lname");
        boolean man = Integer.parseInt(String.valueOf(user.get("manager"))) == 1;
        User u = new User(id, u_email, fname, lname, man);
        return u;
    }

}
