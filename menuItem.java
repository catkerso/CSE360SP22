
public class menuItem {
	private String name;
	private String description;
	private String ingredients;
	private float price;
	private int time;
	private boolean isVegan;
	public menuItem() {
		name = "";
		description = "";
		ingredients = "";
		price = 0;
		isVegan = false;
		time = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String d) {
		description = d;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String I) {
		ingredients = I;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float p) {
		price = p;
	}
	public float getItemTime(){
		return time
	}
	public void setItemTime(int t){
		time = t;
	}
	public boolean isVegan() {
		return isVegan;
	}
}
