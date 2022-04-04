package com.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import com.Objects.MenuItem;
import com.Objects.Order;
import com.Objects.User;

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
        loadDB();
    }
    

    private void loadDB() {
        try {
            FileReader reader = new FileReader(file);
            JSONObject o = (JSONObject) parser.parse(reader);

            this.userObject = (JSONObject) o.get("users");
            this.orderObject = (JSONObject) o.get("orders");
            this.userAuthObject = (JSONObject) o.get("userAuth");
            this.menuItemsObject = (JSONObject) o.get("menuItems");




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
        loadDB();
        return userObject;
    }

    /**
     * returns the authentication object loaded from the db file
     * @return auth object
     */
    protected JSONObject getUserAuthObject() {
        loadDB();
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
        loadDB();
        int index = Integer.parseInt(String.valueOf(userObject.get("idCount")));
        JSONObject user = new JSONObject();
        user.put("email", email);
        user.put("fname", fname);
        user.put("lname", lname);
        user.put("manager", index == 0 ? 1 : 0);

        user.put("phone", "");
        user.put("address", "");
        user.put("rewards", 0);
        user.put("visitCount", 0);
        user.put("cc_fname", "");
        user.put("cc_lname", "");
        user.put("cc_number", "");
        user.put("cc_exp", "");
        user.put("cc_cvv", "");

        
        
        userObject.put("" + index, user);
        userObject.put("idCount", index + 1);
        
        JSONObject authObject = new JSONObject();
        authObject.put("email", email);
        authObject.put("password", password);
        authObject.put("userID", index);
        userAuthObject.put(email, authObject);
        
        writeDB();

        if (index == 0) {
            populateMenu();
        }

        return index;
    }

    /**
     * Edit the user object in the database
     * @param userID
     * @param user
     * @return true if the user was edited, false otherwise
     */
    protected boolean editUser(int userID, User user) {
        loadDB();
        if(userObject.containsKey("" + userID)) {
            JSONObject uObject = (JSONObject) userObject.get("" + userID);
            uObject.put("fname", user.getFirstName());
            uObject.put("lname", user.getLastName());
            uObject.put("manager", user.isManager() ? 1 : 0);
            uObject.put("address", user.getAddress());
            uObject.put("phone", user.getPhoneNumber());
            uObject.put("rewards", user.getRewards());
            uObject.put("visitCount", user.getVisitCount());
            if(user.getCreditCard() != null) {
                uObject.put("cc_fname", user.getCreditCard().getFirstName());
                uObject.put("cc_lname", user.getCreditCard().getLastName());
                uObject.put("cc_number", user.getCreditCard().getCardNumber());
                uObject.put("cc_exp", user.getCreditCard().getExpDate());
                uObject.put("cc_cvv", user.getCreditCard().getCcv());
            } else {
                uObject.put("cc_fname", "");
                uObject.put("cc_lname", "");
                uObject.put("cc_number", "");
                uObject.put("cc_exp", "");
                uObject.put("cc_cvv", "");
            }

            userObject.put("" + userID, uObject);
            writeDB();
            return true;
        }
        return false;
    }


    /**
     * Adds a new menu item to the database and returns the id of the item
     * @param item - the menu item to add
     * @return the id of the item that was added to the database
     */
    protected int addMenuItem(MenuItem item) {
        loadDB();
        int index = Integer.parseInt(String.valueOf(menuItemsObject.get("idCount")));

        JSONObject menuItem = new JSONObject();
        menuItem.put("name", item.getName());
        menuItem.put("description", item.getDescription());
        menuItem.put("ingredients", item.getIngredients());
        menuItem.put("price", item.getPrice());
        menuItem.put("vegan", item.isVegan() ? 1 : 0);
        menuItem.put("time", item.getItemTime());
        menuItem.put("imageURI", item.getImageURI());

        menuItemsObject.put("" + index, menuItem);
        menuItemsObject.put("idCount", index + 1);

        writeDB();

        return index;
    }


    /**
     * Returns the menu item object loaded from the db file
     * @return menu item object
     */
    protected JSONObject getMenuItemsObject() {
        loadDB();
        return menuItemsObject;
    }

    /**
     * Edits the menu item object loaded from the db file
     * with the new values at the specified id and 
     * writes the changes to the db file
     */
	protected void editMenuItem(int id, MenuItem newItem) {
        loadDB();
        JSONObject menuItem = (JSONObject) menuItemsObject.get("" + id);
        menuItem.put("name", newItem.getName());
        menuItem.put("description", newItem.getDescription());
        menuItem.put("ingredients", newItem.getIngredients());  
        menuItem.put("price", newItem.getPrice());
        menuItem.put("vegan", newItem.isVegan() ? 1 : 0);
        menuItem.put("time", newItem.getItemTime());
        menuItem.put("imageURI", newItem.getImageURI());
        menuItemsObject.put("" + id, menuItem);
        writeDB();
    }


    /**
     * removes the menu item object at the specified id
     * @param id - the id of the menu item to remove
     */
    protected void deleteMenuItem(int id) {
        loadDB();
        menuItemsObject.remove("" + id);
        writeDB();
    }


    /**
     * Returns the order object loaded from the db file
     * @return order object
     */
    protected JSONObject getOrderObject() {
        loadDB();
        return orderObject;
    }


    /**
     * Adds a new order object to the database and returns the id of the order
     * @param order - the order to add
     * @return the id of the order that was added to the database
     */
    protected int addOrder(Order order) {
        loadDB();
        int index = Integer.parseInt(String.valueOf(orderObject.get("idCount")));

        JSONObject newOrder = new JSONObject();
        newOrder.put("userID", order.getId());
        newOrder.put("time", order.getTime());
        newOrder.put("price", order.getPrice());

        ArrayList<MenuItem> items = order.getItems();
        JSONArray itemsArray = new JSONArray();
        //add each item id to the array
        for (MenuItem item : items) {
            itemsArray.add(item.getId());
        }
        newOrder.put("items", itemsArray);

        orderObject.put("" + index, newOrder);
        orderObject.put("idCount", index + 1);

        writeDB();

        return index;
    }   


    /**
     * Edits the order object loaded from the db file
     * with the new values at the specified id and 
     * writes the changes to the db file
     */
    protected void editOrder(int id, Order newOrder) {
        loadDB();
        JSONObject order = (JSONObject) orderObject.get("" + id);
        order.put("userID", newOrder.getId());
        order.put("time", newOrder.getTime());
        order.put("price", newOrder.getPrice());

        ArrayList<MenuItem> items = newOrder.getItems();
        JSONArray itemsArray = new JSONArray();
        //add each item id to the array
        for (MenuItem item : items) {
            itemsArray.add(item.getId());
        }
        order.put("items", itemsArray);
        orderObject.put("" + id, order);
        writeDB();
    }



    /**
     * removes the order object at the specified id
     * @param id - the id of the order to remove
     */
    protected void deleteOrder(int id) {
        loadDB();
        orderObject.remove("" + id);
        writeDB();
    }


    /**
     * Pre-populates five menu items
     */
    public void populateMenu() {
        MenuItem item1 = new MenuItem(1, "Burger", "A delicious, grass-fed angus burger.", "Bun, beef, lettuce, and tomatoes.", (float)15.99, false, 14, "https://operahousekc.com/wp-content/uploads/2021/04/burgers.jpg");
        addMenuItem(item1);

        MenuItem item2 = new MenuItem(2, "Steak", "Grade A, angus rib-eye steak.", "Steak, salt, pepper.", (float)39.97, false, 20, "https://cdn.shopify.com/s/files/1/2233/6197/products/32304_-_32312_C_-_Elk_Ribeye_Steak-1_grande.jpg?v=1589575861");
        addMenuItem(item2);
        
        MenuItem item3 = new MenuItem(3, "Fries", "Golden french fries made fresh.", "Potatoes, Ketchup, salt.", (float)6.79, true, 6, "https://sherwoodfoods.com/wp-content/uploads/2015/02/600x400-SFD-french-fries.jpg");
        addMenuItem(item3);
        
        MenuItem item4 = new MenuItem(4, "Quinoa Bowl", "Made healthy and organic with local ingredients.", "Quinoa, Brussels Sprouts, Tomatoes, House Dressing.", (float)14.89, true, 10, "https://thecozyapron.com/wp-content/uploads/2012/03/quinoa-salad_thecozyapron_1.jpg");
        addMenuItem(item4);
        
        MenuItem item5 = new MenuItem(5, "Salad", "Refreshing and energizing garden salad.", "Spinach, Avocado, Peppers, Sweet Potatoes, House Dressing", (float)12.98, true, 8, "https://irenamacri.com/wp-content/uploads/2020/09/rainbow-salad-power-bowl-1-600x400.jpg");
        addMenuItem(item5);

    }

}
