package com.Objects;

public class User {
	private String email;
	private boolean manager;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private float rewards;
	private int visitCount;
	private CreditCard creditCard;
	private int id;
	
	public User() {}
	
	public User (int id, String email, String firstName, String lastName, boolean manager) {
		this.id = id;
		this.email = email;
		this.manager = manager;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = "";
		this.address = "";
		this.rewards = 0;
		this.visitCount = 0;
		this.creditCard = null;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public float getRewards() {
		return rewards;
	}
	
	public void setRewards(float rewards) {
		this.rewards = rewards;
	}
	
	public int getVisitCount() {
		return visitCount;
	}
	
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

    public String getFullName() {
        return firstName + " " + lastName;
    }

	public boolean isManager() {
		return manager;
	}

	public int getId() {
		return id;
	}

}
