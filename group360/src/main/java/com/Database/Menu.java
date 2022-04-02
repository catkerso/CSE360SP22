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
        m.setPrice(Float.parseFloat(String.valueOf(item.get("price"))));
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

    
    /**
     * Delete a menu item from the database with the specified id
     */
    public static void deleteMenuItem(int id) {
        App.db.deleteMenuItem(id);
    }


    /**
     * Pre-populates five menu items
     */
    public static void populateMenu() {
        MenuItem item1 = new MenuItem(1, "Burger", "A delicious, grass-fed angus burger.", "Bun, beef, lettuce, and tomatoes.", (float)15.99, false, 14, "https://operahousekc.com/wp-content/uploads/2021/04/burgers.jpg");
        App.db.addMenuItem(item1);

        MenuItem item2 = new MenuItem(2, "Steak", "Grade A, angus rib-eye steak.", "Steak, salt, pepper.", (float)39.97, false, 20, "https://cdn.shopify.com/s/files/1/2233/6197/products/32304_-_32312_C_-_Elk_Ribeye_Steak-1_grande.jpg?v=1589575861");
        App.db.addMenuItem(item2);
        
        MenuItem item3 = new MenuItem(3, "Fries", "Golden french fries made fresh.", "Potatoes, Ketchup, salt.", (float)6.79, true, 6, "https://sherwoodfoods.com/wp-content/uploads/2015/02/600x400-SFD-french-fries.jpg");
        App.db.addMenuItem(item3);
        
        MenuItem item4 = new MenuItem(4, "Quinoa Bowl", "Made healthy and organic with local ingredients.", "Quinoa, Brussels Sprouts, Tomatoes, House Dressing.", (float)14.89, true, 10, "https://thecozyapron.com/wp-content/uploads/2012/03/quinoa-salad_thecozyapron_1.jpg");
        App.db.addMenuItem(item4);
        
        MenuItem item5 = new MenuItem(5, "Salad", "Refreshing and energizing garden salad.", "Spinach, Avocado, Peppers, Sweet Potatoes, House Dressing", (float)12.98, true, 8, "https://irenamacri.com/wp-content/uploads/2020/09/rainbow-salad-power-bowl-1-600x400.jpg");
        App.db.addMenuItem(item5);

    }

}
