package cn.cart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon {
	private static Connection connetion = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connetion == null) {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:myDB.db";
			connetion = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			// SQL statement for creating a new table
			String sqlProduct = "CREATE TABLE IF NOT EXISTS products (\n"
					+ " id integer NOT NULL PRIMARY KEY,\n"
					+ " name text NOT NULL,\n"
					+ " category text NOT NULL,\n"
					+ " price double NOT NULL,\n"
					+ " image text NOT NULL\n"
					+ ");";
			String sqlOrders = "CREATE TABLE IF NOT EXISTS orders (\n"
					+ " id integer NOT NULL PRIMARY KEY,\n"
					+ " p_id integer NOT NULL,\n"
					+ " u_id integer NOT NULL,\n"
					+ " o_quantity integer NOT NULL,\n"
					+ " o_date text NOT NULL\n"
					+ ");";
			String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
					+ " id integer NOT NULL PRIMARY KEY,\n"
					+ " name text NOT NULL,\n"
					+ " email text NOT NULL,\n"
					+ " password text NOT NULL\n"
					+ ");";
			String inserProduct = "INSERT INTO products (name, category, price, image) values (?,?,?,?)";
			String inserUser = "INSERT INTO users (name, email, password) values ('test','test@gmail.com','test123')";
			String inserAdmin = "INSERT INTO users (name, email, password) values ('admin','admin@gmail.com','admin123')";
			 try{  
		            Connection conn = DriverManager.getConnection(url);  
		            Statement stmt = conn.createStatement();  
		            stmt.execute(sqlProduct);
		            stmt.execute(sqlOrders); 
		            stmt.execute(sqlUsers);
		            stmt.execute(inserUser);
		            stmt.execute(inserAdmin);
		            PreparedStatement pst = conn.prepareStatement(inserProduct);
		            pst.setString(1, "Female Shoes");
		            pst.setString(2, "SHoes");
		            pst.setDouble(3, 100.00);
		            pst.setString(4, "female-shoes.jpg");
		            pst.executeUpdate();
		            
		            PreparedStatement pst1 = conn.prepareStatement(inserProduct);
		            pst1.setString(1, "Ladies Bag");
		            pst1.setString(2, "Bag");
		            pst1.setDouble(3, 300.00);
		            pst1.setString(4, "ladis-bag.jpg");
		            pst1.executeUpdate();
		            PreparedStatement pst2 = conn.prepareStatement(inserProduct);
		            pst2.setString(1, "Men Suits");
		            pst2.setString(2, "Suits");
		            pst2.setDouble(3, 200.00);
		            pst2.setString(4, "men-suits.jpg");
		            pst2.executeUpdate();
		            PreparedStatement pst3 = conn.prepareStatement(inserProduct);
		            pst3.setString(1, "Men Watch");
		            pst3.setString(2, "Watch");
		            pst3.setDouble(3, 50.00);
		            pst3.setString(4, "men-watch.jpg");
		            pst3.executeUpdate();
		            System.out.println("Data Added");
		        } catch (SQLException e) {  
		            System.out.println(e.getMessage());  
		        }
		}
		return connetion;
	}
}
