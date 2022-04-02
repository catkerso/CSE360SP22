package com.Database;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

import com.Objects.CreditCard;
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
            authedUser = getUserById(Integer.parseInt(uid));
            isAuthed = true;
            return authedUser;
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
        return App.db.getUserAuthObject().containsKey(email);
    }


    /**1
     * Returns the user with the given id
     * @return User object of the user with the id given. Will return null if no user is found.
     */
    public static boolean doesUserExist(int id) {
        return App.db.getUserObject().containsKey("" + id);
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


    /**
     * Checks whether or not a given string is a 10 digit phone number
     */
    public static boolean isValidPhoneNumber(String number) {
        //regex phone number with option dashes and paranthesis and spaces
        return Pattern.compile("^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$").matcher(number).matches();
    }


    /**
     * Returns a list of all users
     * @return list of all users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        JSONObject usersObject = (JSONObject) App.db.getUserObject();

        Set<String> keys = usersObject.keySet();

        for (String key : keys) {
            if(key.equals("idCount")) continue;
            users.add(getUserById(Integer.parseInt(key)));    
        }

    return users;
    }


    /**
     * Returns a user object by id
     * @param id
     * @return user object
     */
    public static User getUserById(int id) {
        JSONObject userObject = App.db.getUserObject();
        if(!userObject.containsKey("" + id)) return null;
        userObject = (JSONObject) userObject.get("" + id);
        String  email = (String) userObject.get("email");
        String  fname = (String) userObject.get("fname");
        String  lname = (String) userObject.get("lname");
        boolean man = Integer.parseInt(String.valueOf(userObject.get("manager"))) == 1;
        User user = new User(id, email, fname, lname, man);


        String address = (String) userObject.get("address");
        String phone = (String) userObject.get("phone");
        float rewards = Float.parseFloat(String.valueOf(userObject.get("rewards")));
        int visitCount = Integer.parseInt(String.valueOf(userObject.get("visitCount")));
        
        String cc_number = (String) userObject.get("cc_number");
        String cc_fname = (String) userObject.get("cc_fname");
        String cc_lname = (String) userObject.get("cc_lname");
        String cc_exp = (String) userObject.get("cc_exp");
        String cc_cvv = (String) userObject.get("cc_cvv");
        
        if(cc_number.length() > 0) {
            CreditCard card = new CreditCard(cc_fname, cc_lname, cc_number, cc_exp, cc_cvv);
            user.setCreditCard(card);
        }
        

        user.setAddress(address);
        user.setPhoneNumber(phone);
        user.setRewards(rewards);
        user.setVisitCount(visitCount);

        return user;
    }





    /**
     * 
     * @param id
     * @param user
     */
    public static void editUser(int id, User user) {
        App.db.editUser(id, user);
    }
    



}
