package com.Objects;

import java.util.ArrayList;

public class Order {
	private ArrayList<MenuItem> items;
    private int id;
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
     * sets the price of the order
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * sets the time of the order
     */
    public void setTime(int time) {
        this.time = time;
    }


    /**
     * Creates a new order object by the user and with the provided items
     * @param user the user who placed the order
     * @param items  the items in the order
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
     * @param item to add to the order
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
		price = 0;
        for (int i = 0; i < items.size(); i++){
			price = price + items.get(i).getPrice();
		}    
	}

    /**
     * get the price of the order
     * @return price of the order
     */
	public float getPrice() {
        calculatePrice();
		return price;
	}
 
    /**
     * calculates and sets the time it takes to create the order
     */
	private void calculateTime() {
        time = 0;
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

    /**
     * gets the id of the order
     * @return id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the order
     * @param id of the order
     */
    public void setId(int id) {
        this.id = id;
    }


}

