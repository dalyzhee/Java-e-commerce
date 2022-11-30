package cn.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import cn.cart.model.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		this.con = con;
	}
	public boolean insertProducts(Product product){
		boolean result = false;
		try {
			query = "insert into products (name, category, image, price) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getCategory());
			pst.setString(3, product.getImage());
			pst.setDouble(4, product.getPrice());
			pst.executeUpdate();
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;			
	}
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			query = "select * from products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
            	Product row = new Product();
            	row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                products.add(row);
            }
//			product1 = new Product();
//			product2 = new Product();
//			product.setId(1);
//			product.setCategory("Shoe");
//			product.setPrice(400.00);
//			product.setName("Female Shoe");
//			product.setImage("female-shoes.jpg");
//			product1.setId(2);
//			product1.setCategory("Bag");
//			product1.setPrice(300.00);
//			product1.setName("Ladies Bag");
//			product1.setImage("ladis-bag.jpg");
//			product2.setId(3);
//			product2.setCategory("suit");
//			product2.setPrice(100.00);
//			product2.setName("Men Suit");
//			product2.setImage("men-suits.jpg");
//			products.add(product);
//			products.add(product1);
//			products.add(product2);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                    
//					row.setId(1);
//					row.setName("Female Shoes");
//					row.setCategory("Shoe");
//					row.setImage("female-shoes.jpg");
//					row.setPrice(400.00*item.getQuantity());
//					row.setQuantity(item.getQuantity());
//					row1.setId(2);
//					row1.setName("Ladies Bag");
//					row1.setCategory("Bag");
//					row1.setImage("ladis-bag.jpg");
//					row1.setPrice(300.00*item.getQuantity());
//					row1.setQuantity(item.getQuantity());
//					products.add(row);
//					products.add(row1);
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return products;
		
	}
	
	
	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from products where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
			}
//			row = new Product();
//			row.setId(1);
//			row.setName("Levis");
//			row.setCategory("Shoe");
//			row.setPrice(100.00);
//			row.setImage("female-shoes.jpg");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if(cartList.size()>0) {
				for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("price")*item.getQuantity();
                    }

                }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

}
