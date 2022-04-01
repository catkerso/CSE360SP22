package com.Database;

import java.util.ArrayList;

import com.Objects.MenuItem;
import com.Objects.Order;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ViewControllers.App;

public class Orders {
    



    /**
     * Gets the order from  the database with the specified id
     * @param id - the id of the order to get
     * @return the order with the specified id
     */
    Order getOrderById(int id) {
        JSONObject orderObject = App.db.getOrderObject();
        JSONObject order = (JSONObject) orderObject.get(String.valueOf(id));
        int userId = Integer.parseInt(String.valueOf(order.get("userId")));

        JSONArray itemsArray = (JSONArray) order.get("items");
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        for (Object item : itemsArray) {
            items.add(Menu.getMenuItem(Integer.parseInt(String.valueOf(item))));
        }

        Order o = new Order(Auth.getUserById(userId));
        o.setId(id);
        o.setPrice(Float.parseFloat(String.valueOf(order.get("price"))));
        o.setTime(Integer.parseInt(String.valueOf(order.get("time"))));
        o.setItems(items);
        return o;   
    }

    /**
     * Adds a new order to the database and returns the id of the order
     * @param order - the order to add
     * @return the id of the order that was added to the database
     */
    int addOrder(Order order) {
        return App.db.addOrder(order);
    }


    /**
     * Edits the order object loaded from the db file
     * with the new values at the specified id and 
     * writes the changes to the db file
     */
    void editOrder(int id, Order newOrder) {
        App.db.editOrder(id, newOrder);
    }


    /**
     * Deletes the order object with the specified id
     */
    void deleteOrder(int id) {
        App.db.deleteOrder(id);
    }

}
