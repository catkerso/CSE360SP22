package cse360project;

public class CreditCard {
	private String firstName;
	private String lastName;
	private String cardNumber;
	private String expDate;
	private String ccv;
	
	public CreditCard() {}
	
	public CreditCard (String firstName, String lastName, String cardNumber, String expDate, String ccv) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.ccv = ccv;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber (String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getExpDate() {
		return expDate;
	}
	
	public void setExpDate (String expDate) {
		this.expDate = expDate;
	}
	
	public String getCcv() {
		return ccv;
	}
	
	public void setCcv (String ccv) {
		this.ccv = ccv;
	}
}
