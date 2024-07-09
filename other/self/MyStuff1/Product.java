package bs10x.var;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Product extends StreamLambdas {
	private int ID;
	private String name;
	private double price;
	
	public Product(int ID, String name, double price) {
		super();
		this.ID = ID;
		this.name = name;
		this.price = price;
	}
	
	public void setID(int ID) { this.ID = ID; }
	public void setPrice(double price) { this.price = price; }
	public void setName(String name) { this.name = name; }

	public int getID() { return ID; }
	public String getName() { return name; }
	public double getPrice() { return price; }
	
	
	public static List<Optional<Product>> makeProductList(int numbOfProduct, int numbOfEmpty) {
		
		List<Optional<Product>> productList = Collections.synchronizedList(new ArrayList<>());
		
		Thread productThread = new Thread(() -> {
			for (int i = 1; i < numbOfProduct + 1; i++) {
				productList.add(Optional.of(new Product(i, String.valueOf(i), (double) i)));
			}
		});
		
		Thread emptyThread = new Thread(() -> {
			for (int i = 1; i < numbOfEmpty; i++) {
				productList.add(Optional.empty());
			}
		});
		
		productThread.start();
		emptyThread.start();
		
		return productList;
	}
	
	

}

/*
 * Create a class Product with attributes like id, name, and price.
 * Create a list of Optional<Product> to represent a list of products where some products might be missing (empty optionals).
 * Using the Stream API, compute the average price of all products that are present (non-empty optionals).
 * Print the average price or a message indicating that no products are available if the list is empty.
 * 
 */
