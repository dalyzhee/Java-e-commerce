package cn.cart.model;

import java.sql.Blob;

public class Product {
	private int id;
	private String category;
	private Double price;
	private String image;
	private String name;
	
	
	
	public Product() {
	}



	public Product(int id, String category, Double price, String image, String name) {
		this.id = id;
		this.category = category;
		this.price = price;
		this.image = image;
		this.name = name;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", price=" + price + ", image=" + image + ", name="
				+ name + "]";
	}


	
	
	
	
	
	
	
}
