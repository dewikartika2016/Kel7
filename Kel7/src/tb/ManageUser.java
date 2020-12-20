package tb;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class ManageUser {
	static String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static String USERNAME = "root";
    static String PASSWORD = "";
    static Scanner scanner = new Scanner(System.in);
    
	static Connection connection;
	
public ManageUser() {
		
		try {
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<User> getAll() {
    	
		ArrayList<User> ListUser = new ArrayList<>();
    	Statement statement;
    	
    	try {
			statement = connection.createStatement();
			
			String sql = "SELECT username, email, password FROM user";
			
			ResultSet rs = statement.executeQuery(sql);
		
			
			
			while(rs.next()) {
				
				User user = new User(
						rs.getString("username"),
						rs.getString("email"),
						rs.getString("password")
						);
				ListUser.add(user);
			}
			
			
			if(ListUser.isEmpty()) System.out.println("User Kosong");
			 
    		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
    	return ListUser;
    }
    
    public int tambahData() {
    	
    	Integer result = 0;
        System.out.print("Username : ");
        String username = scanner.nextLine();
        
        System.out.print("Email : ");
        String email = scanner.nextLine();
        
        System.out.print("Password : ");
        String password = scanner.nextLine();
        
        String sql = "INSERT INTO user (username, email, password) VALUES(?,?,?)";
                
                try {
                    PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.setString(2, email);
                    statement.setString(3, password);
                    result = statement.executeUpdate();
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
    	return result;
    	
    	
    }
    
    public ArrayList<User> cari() {
    	
    	ArrayList<User> ListUser = new ArrayList<>();
    	if(!getAll().isEmpty()) {
    	
        System.out.print("Masukkan Key (username) : ");
        String keyword = scanner.nextLine();
        
        PreparedStatement statement;
		try {
			String sql = "SELECT * FROM user WHERE username LIKE ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, "%"+keyword+"%");
			
			ResultSet rs = statement.executeQuery();
		        
		        if(rs.next()){
		        	
		        	User user = new User(
							
							rs.getString("username"),
							rs.getString("email"),
							rs.getString("password")
							);
		        	ListUser.add(user);
		        	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
      return ListUser;
      
    	
    }
    
    public int edit() {
    	
    		Integer result=0;
            
            System.out.print("\nUsername Diedit ? :  ");
            String id = scanner.nextLine();
            
            String sql = "SELECT * FROM user WHERE username = ?";
            
            try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
          
            
            if(rs.next()){
                
            	
                System.out.print("Username ["+rs.getString("username")+"]: ");
                String username = scanner.nextLine();
                
                System.out.print("Email ["+rs.getString("email")+"]: ");
                String email = scanner.nextLine();
                
                System.out.print("Password ["+rs.getString("password")+"]: ");
                String password = scanner.nextLine();

                String update = "UPDATE user SET username = ? , email = ?, password = ? WHERE username = ? "; 
                
                statement = connection.prepareStatement(update);
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, id);
                result = statement.executeUpdate();
                
                
             
            }
            else {
            	System.out.println("User Tidak Tersedia");
            }
            
            }catch (SQLException e) {
				System.out.println(e.getMessage());
			}
                  
        return result;
    	
    	
    }

}

