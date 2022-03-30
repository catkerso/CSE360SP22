package com.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.Objects.MenuItem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DB {
    private File file;
    private JSONParser parser;
    private JSONObject userObject;
    private JSONObject orderObject;
    private JSONObject userAuthObject;
    private JSONObject menuItemsObject;


    /**
     * Creates a new database object from the file location specified
     * @param fileName - the location of the file to create the db from
     */
    public DB(String fileName) {
        
        file = new File(fileName);
        parser = new JSONParser();
        
        if(!file.exists()) {
            createDatabase();
        }
        
        //Parse the json object
        try {
            FileReader reader = new FileReader(file);
            JSONObject o = (JSONObject) parser.parse(reader);

            this.userObject = (JSONObject) o.get("users");
            this.orderObject = (JSONObject) o.get("orders");
            this.userAuthObject = (JSONObject) o.get("userAuth");




        } catch (FileNotFoundException e) {
            System.out.println("This should never be reached");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Writes the currently stored object to the databse
     */
    private void writeDB() {
        JSONObject db = new JSONObject();
        
        db.put("users", userObject);
        db.put("orders", orderObject);
        db.put("menuItems", menuItemsObject);
        db.put("userAuth", userAuthObject);
        
        ///writes the the json object to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(db.toJSONString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("the error is here");
           e.printStackTrace(); 
        }
    }


    /**
     * Creates a new datatbase at the file location; to be used
     * when a database is not found at the specified file location
     */
    private void createDatabase() {

        //declare objects
        JSONObject db = new JSONObject();
        JSONObject usersObject = new JSONObject();
        JSONObject ordersObject = new JSONObject();
        JSONObject menuItemsObject = new JSONObject();
        JSONObject userAuthObject = new JSONObject();


        // init key counters to 0
        usersObject.put("idCount", 0);
        ordersObject.put("idCount", 0);
        menuItemsObject.put("idCount", 0);
        
        
        //add the objects to the db object
        db.put("users", usersObject);

        
        db.put("orders", ordersObject);
        db.put("menuItems", menuItemsObject);
        db.put("userAuth", userAuthObject);

        
        //writes the the json object to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(db.toJSONString());
            writer.flush();
        } catch (IOException e) {
           e.printStackTrace(); 
        }
        
    }

    /**
     * returns the users objecy loaded from the db file
     * @return users object
     */
    protected JSONObject getUserObject() {
        return userObject;
    }

    /**
     * returns the authentication object loaded from the db file
     * @return auth object
     */
    protected JSONObject getUserAuthObject() {
        return userAuthObject;
    }
    
    /**
     * Adds a new user object to the database
     * @param email
     * @param password - prehashed
     * @param fname
     * @param lname
     * @return the id of the user that was added to the database
     */
    protected int addUser(String email, String password, String fname, String lname) {
        int index = (int) (long) userObject.get("idCount");
        JSONObject user = new JSONObject();
        user.put("email", email);
        user.put("fname", fname);
        user.put("lname", lname);
        user.put("manager", index == 0 ? 1 : 0);

        userObject.put("" + index, user);
        userObject.put("idCount", index + 1);
        
        JSONObject authObject = new JSONObject();
        authObject.put("email", email);
        authObject.put("password", password);
        authObject.put("userID", index);
        userAuthObject.put(email, authObject);
        

        writeDB();

        return index;
    }
    


    protected int addMenuItem(MenuItem item) {
        int index = (int) (long) menuItemsObject.get("idCount");
        JSONObject menuItem = new JSONObject();
        menuItem.put("name", item.getName());
        menuItem.put("description", item.getDescription());
        menuItem.put("ingredients", item.getIngredients());
        menuItem.put("price", item.getPrice());
        menuItem.put("vegan", item.isVegan());
        menuItem.put("time", item.getItemTime());
        
        menuItemsObject.put("" + index, menuItem);
        menuItemsObject.put("idCount", index + 1);

        writeDB();

        return index;
    }





}
