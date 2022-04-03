package com.Objects;

import javafx.scene.image.Image;

public class MenuItem {
    private int id;
	private String name;
	private String description;
	private String ingredients;
	private float price;
	private int time;
	private boolean vegan;
    private String url;
    private Image image;

    /**
     * creates a blank menu item object
     */
	public MenuItem() {
    
	}
    
    /**
     * creates a new menu item with the properties specified 
     * @param name
     * @param desc
     * @param ing
     * @param price
     * @param isVegan
     * @param time
     * @param imageURI
     */
    public MenuItem(int id, String name, String desc, String ing, float price, boolean vegan, int time, String imageURI) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.ingredients = ing;
        this.price = price;
        this.vegan = vegan;
        this.time = time;
        this.url = imageURI;
        setImage(imageURI);
    }
    

    /**
     * gets the time of the menu item
     * @return time of the menu item
     */ 
    public int getTime() {
        return time;
    }

    /**
     * sets the time of the menu item
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * gets the id of the menu item
     * @return id of the menu item
     */
    public String getImageURI() {
        return url;
    }

    /**
     * sets the image URI of the menu item
     * @param imageURI
     */
    public void setImageURI(String imageURI) {
        this.url = imageURI;
    }

    /**
     * returns the menu items id
     * @return the menu items id
     */
    public int getId() {
        return id;
    }
    
    /**
     * name of the menu item 
     * @return menu item name
     */ 
	public String getName() {
		return name;
	}

    /**
     * set the name of the menu item
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}

    
    /**
     * Gets the description of the menu item
     * @return description of the menu item
     */
	public String getDescription() {
		return description;
	}


    /**
     * Sets the description of the menu item
     * @param description
     */
	public void setDescription(String desc) {
		description = desc;
	}


    /**
     * gets the ingredients string of the menu item
     * @return ingredients string
     */
	public String getIngredients() {
		return ingredients;
	}

    /**
     * sets the ingredient string of the menu item
     * @param ingredients
     */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

    /**
     * gets the price of the menu item
     * @return price
     */ 
	public float getPrice() {
		return price;
	}
     
    /**
     * sets the price of the menu item
     * @param price
     */
	public void setPrice(float price) {
		this.price = price;
	}

    /**
     * gets the time it takes to create the menu item
     * @return time 
     */
	public int getItemTime(){
		return time;
	}
    
    /**
     * sets the time it trakes tot create the menu item
     * @param time
     */
	public void setItemTime(int time){
		this.time = time;
	}

    /**
     * returns whether or not the menu item is vegan
     * @return vegan
     */
	public boolean isVegan() {
		return vegan;
	}
    
    /**
     * sets whether an item is vegan or not
     * @param vegan
     */
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    /**
     * sets the id of the menu item
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets the image of the menu item
     * @param imageURI
     */
    public void setImage(String imageURI) {
        this.image = new Image(imageURI);
    }
    
    /**
     * gets the image of the menu item
     * @return Image object 
     */
    public Image getImage() {
        return image;
    }

}
