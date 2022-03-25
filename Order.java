import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private ArrayList<MenuItem> items;
	private ArrayList<MenuItem> currItems;
	private float price;
	private int time; 
	private Customer customer;
	
	public Order() {
		items = new ArrayList<MenuItem>;
		currItem = new ArrayList<MenuItem>;
		customer = new Customer;
	}
	
	public List<MenuItem> getItems() {
		return items;
	}
	public void setItems(List<MenuItem> a) {
		//
		items = (ArrayList<MenuItem>) a;
	}
	public void addItem(List<MenuItem> adItm){
		currItems.add(adItm);
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float p) {
		price = p;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer c) {
		customer = c;
	}
	public float rollTotalPrice() {
		for (int i = 0; i < currItems.length(); i++){
			price = price + currItems.get(i).getPrice();
		}
	}
	public int getTime() {
		return time;
	}
	public int calculateTime() {
		for (int i = 0; i < currItems.length(); i++){
			time = time + currItems.get(i).getItemTime();
		}
	}
}
