package com.Objects;

import java.util.ArrayList;

public class Order {
	private ArrayList<MenuItem> items;
	private float price;
	private int time; 
	private User customer;
	

    /**
     * creates a blank order object by the provided user
     * @param user
     */
	public Order(User user) {
        this.customer = user;
		items = new ArrayList<MenuItem>();
	}
    
    
    /**
     * Creates a new order object by the user and with the provided items
     * @param user
     * @param items
     */
    public Order(User user, ArrayList<MenuItem> items) {
        this.customer = user;
        this.items = items;
    }
	
    /**
     * gets a list of menu items in the order
     * @return menu items in the order
     */
	public ArrayList<MenuItem> getItems() {
		return items;
	}
    
    /**
     * sets the menu items to the list provided
     * @param menu items for the order
     */
	public void setItems(ArrayList<MenuItem> a) {
		items = (ArrayList<MenuItem>) a;
	}
    
    /**
     * add a menu item to the order
     * @param item
     */
	public void addItem(MenuItem item){
		items.add(item);
	}
    
   
    /**
     * gets the user object that made the order
     * @return
     */
	public User getCustomer() {
		return customer;
	}
    
    /**
     * calculates and sets the price of the order
     */
	private void calculatePrice() {
		for (int i = 0; i < items.size(); i++){
			price = price + items.get(i).getPrice();
		}    
	}

    /**
     * get the price of the order
     * @return
     */
	public float getPrice() {
        calculatePrice();
		return price;
	}
 
    /**
     * calculates and sets the time it takes to create the order
     */
	private void calculateTime() {
		for (int i = 0; i < items.size(); i++){
			time = time + items.get(i).getItemTime();
		}
	}
    
    /** 
     * get the time it takes to create the order 
     */ 
	public int getTime() {
        calculateTime();
		return time;
	}
}

