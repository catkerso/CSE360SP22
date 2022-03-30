package com.Database;

import java.util.ArrayList;

import com.Objects.MenuItem;

public class Menu {
    

    /**
     * Get a MenuItem object from the Id of the object
     * @param id
     * @return MenuItem
     */
    public MenuItem getMenuItem(int id) {
        
        return new MenuItem();
    }

    /**
     * Returns the list of all current menu objects
     * @return all current menu objects
     */
    public ArrayList<MenuItem> getMenu() {
        
        return new ArrayList<>();
    }


    /**
     * Adds a new menu item to the database
     * @param menuItem
     * @return
     */
    public boolean addMenuItem(MenuItem menuItem) {

        return false;
    }

    /**
     * 
     * @param id of the item to change
     * @param newItem - MenuItem object with the new properties to overwrite the old item;
     * @return the success of the change
     */
    public boolean editMenuItem(int id, MenuItem newItem) {

        return false;
    }

    
}
