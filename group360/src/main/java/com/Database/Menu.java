package com.Database;

import java.util.ArrayList;
import java.util.Set;

import com.Objects.MenuItem;
import com.ViewControllers.App;

import org.json.simple.JSONObject;

public class Menu {
    

    /**
     * Get a MenuItem object from the Id of the object
     * @param id
     * @return MenuItem
     */
    public static MenuItem getMenuItem(int id) {
        JSONObject item = (JSONObject) App.db.getMenuItemsObject().get(String.valueOf(id));
        MenuItem m = new MenuItem();
        m.setName((String) item.get("name"));
        m.setDescription((String) item.get("description"));
        m.setPrice((float) item.get("price"));
        m.setId(id);
        m.setVegan(Integer.parseInt(String.valueOf(item.get("vegan"))) == 1);
        return m;
    }

    /**
     * Returns the list of all current menu objects
     * @return all current menu objects
     */
    public static ArrayList<MenuItem> getMenu() {
        JSONObject menuObject = (JSONObject) App.db.getMenuItemsObject();
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();

        Set<String> keys = menuObject.keySet();
        System.out.println(keys.toArray()[0]);


        for(String key : keys) {
            if(key.equals("idCount")) continue;
            
            JSONObject item = (JSONObject) menuObject.get(key);
            int id = Integer.parseInt((String) key);
            String name = (String) item.get("name");
            String description = (String) item.get("description");
            String ingredients = (String) item.get("ingredients");
            float price = Float.parseFloat(String.valueOf(item.get("price")));
            int time = Integer.parseInt(String.valueOf(item.get("time")));
            boolean vegan = Integer.parseInt(String.valueOf(item.get("vegan"))) == 1;
            String imageURI = (String) item.get("imageURI");
            MenuItem m = new MenuItem(id, name, description, ingredients, price, vegan, time, imageURI);
            menu.add(m);
        };

        return menu;
    }


    /**
     * Adds a new menu item to the database
     * @param menuItem
     * @return the id where the menu item was added
     */
    public static int addMenuItem(MenuItem menuItem) {
        return App.db.addMenuItem(menuItem);
    }

    /**
     * 
     * @param id of the item to change
     * @param newItem - MenuItem object with the new properties to overwrite the old item;
     * @return the success of the change
     */
    public static boolean editMenuItem(int id, MenuItem newItem) {
        if(getMenuItem(id) == null) {
            return false;
        };
        App.db.editMenuItem(id, newItem);
        return true;
    }

    
}
