package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

//import com.mysql.jdbc.Statement;

public class Datebase {
	private String base;
	
	public Datebase (String b) {
		base = b;
	}


	 public Connection getConnection() throws Exception{
	  try{
	   String driver = "com.mysql.jdbc.Driver";
	   String url = "jdbc:mysql://localhost:3306/Monitors";
	   String username = "root";
	   String password = "";
	   Class.forName(driver);
	   
	   Connection conn = DriverManager.getConnection(url,username,password);
	   PreparedStatement update = conn.prepareStatement("USE `"+base+"`;");
	   update.executeUpdate();
	   System.out.println("Connected");
	   return conn;
	  } catch(Exception e){System.out.println(e);}
	  
	  
	  return null;
	 }
	 

	 
	 private void sendStatement(String insert) {
		 try {
			 Connection conn = getConnection();
			 PreparedStatement statement = conn.prepareStatement(insert);
			 statement.executeUpdate();
		 }	catch (Exception e) {
		// TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public long add (String field, String value) {
			Connection conn;
			long r = 0;
			try {
				conn = getConnection();
			//add("model","78978");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO `Monitors`.`"+base+"` (`"+field+"`) VALUES ('"+value+"');", Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			result.next();
			r = result.getLong(1);
			result.close();
			conn.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long id = r;
			return id;
		 
	 }
	 
	 public String getElement(String field, long id) {
			Connection conn;
			String r = null;
			try {
			conn = getConnection();
		
			PreparedStatement statement = conn.prepareStatement("SELECT "+field+" FROM monitors WHERE id="+id+";");
			ResultSet result = statement.executeQuery();
			result.next();
			r = result.getString(field);
			result.close();
			conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
	 }
	 
	 public long lessThan (long id, String field) {
		 
		 
		return id;
		 
	 }
	 
	 private ResultSet getResult (String s) {
			Connection conn;
			ResultSet result = null;
			try {
				conn = getConnection();
		
			PreparedStatement statement = conn.prepareStatement(s);
			result = statement.executeQuery();
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		 
	 }
	 
	 

	 
	 public ArrayList<String> getElement(int id) {
		 try {
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT model, weight, size, resolution FROM monitors WHERE id="+id+";");
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			result.next();
			array.add(result.getString("model"));
			array.add(result.getString("weight"));
			array.add(result.getString("size"));
			array.add(result.getString("resolution"));
			System.out.print(result.getString("model"));
			System.out.print(" ");
			System.out.print(result.getString("weight"));
			System.out.print(" ");
			System.out.print(result.getString("size"));
			System.out.print(" ");
			System.out.print(result.getString("resolution"));
			System.out.println();
			return array;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 //add new monitor

	 
	 public  void change (long id , String field, String value) {
		 String insert = "UPDATE `Monitors`.`"+base+"` SET `"+field+"` = '"+value+"' WHERE `id` = "+id+";";
		 sendStatement(insert);
		 
	 }
	 
	 public void delete (long id) {
		 String statement = "DELETE FROM `Monitors`.`"+base+"` WHERE  `id`="+id+";";
		 sendStatement(statement);
		 
	 }
	 
	 

	public static void main(String[] args) throws Exception {
		Datebase base = new Datebase("monitors");
		base.getConnection();
		int a = 8;
		int b = 15;
		int c = 50;
		
		
		//System.out.println(base.getElement("model", 93));
		//System.out.println(base.getLastID());
		//base.delete(9);
		//base.change(13, "size", "6");

	}

}
