package tb;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class ManageBarang {
	static String DB_URL = "jdbc:mysql://localhost/tbbpl?serverTimezone=Asia/Jakarta";
	static String USERNAME = "root";
    static String PASSWORD = "";
    static Scanner scanner = new Scanner(System.in);
    
	static Connection connection;
	
public ManageBarang() {
		
		try {
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Barang> getAll() {
    	
		ArrayList<Barang> listBarang = new ArrayList<>();
    	Statement statement;
    	
    	try {
			statement = connection.createStatement();
			
			String sql = "SELECT * FROM barang";
			
			ResultSet rs = statement.executeQuery(sql);
		
			
			
			while(rs.next()) {
				
				Barang barang = new Barang(
						
						rs.getString("sku"),
						rs.getString("nama"),
						rs.getInt("stock"),
						rs.getInt("harga_beli"),
						rs.getInt("harga_jual")
						
						);
				listBarang.add(barang);
			}
			
			
			if(listBarang.isEmpty()) System.out.println("Barang Kosong");
			 
    		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    	
    	return listBarang;
    }
    
    public int tambahData() {
    	
    	Integer result = 0;
        System.out.print("SKU \t\t: ");
        String sku = scanner.nextLine();
        
        System.out.print("Nama Barang \t: ");
        String nama = scanner.nextLine();
        
        System.out.print("Stok \t\t: ");
        Integer stok = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Harga Beli \t: ");
        Integer hBeli = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Harga Jual \t: ");
        Integer hJual = Integer.parseInt(scanner.nextLine());
        
        String sql = "INSERT INTO barang (sku, nama, stock, harga_beli, harga_jual) VALUES(?,?,?,?,?)";
                
                try {
                    PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
                    statement.setString(1, sku);
                    statement.setString(2, nama);
                    statement.setInt(3, stok);
                    statement.setInt(4, hBeli);
                    statement.setInt(5, hJual);
                    result = statement.executeUpdate();
                    
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
    	return result;
    	
    	
    }
    
    public ArrayList<Barang> cari() {
    	
    	ArrayList<Barang> listBarang = new ArrayList<>();
    	if(!getAll().isEmpty()) {
    	
        System.out.print("Masukkan Key (sku) : ");
        String keyword = scanner.nextLine();
        
        PreparedStatement statement;
		try {
			String sql = "SELECT * FROM barang WHERE sku LIKE ?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, "%"+keyword+"%");
			
			ResultSet rs = statement.executeQuery();
		        
		        if(rs.next()){
		        	
		        	Barang barang = new Barang(
							
							rs.getString("sku"),
							rs.getString("nama"),
							rs.getInt("stock"),
							rs.getInt("harga_beli"),
							rs.getInt("harga_jual")
		        			);
		        	listBarang.add(barang);
		        	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
      return listBarang;
      
    	
    }
    
    public int edit() {
    	
    		Integer result=0;
            
            System.out.print("\nSKU Barang Diedit ? :  ");
            String id = scanner.nextLine();
            
            String sql = "SELECT * FROM barang WHERE sku = ?";
            
            try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
          
            
            if(rs.next()){
                
            	
                System.out.print("SKU ["+rs.getString("sku")+"]: ");
                String sku = scanner.nextLine();
                
                System.out.print("Nama ["+rs.getString("nama")+"]: ");
                String nama = scanner.nextLine();
                
                System.out.print("Stok ["+rs.getInt("stock")+"]: ");
                Integer stok = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Harga Beli ["+rs.getInt("harga_beli")+"]: ");
                Integer hBeli = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Harga Jual ["+rs.getInt("harga_jual")+"]: ");
                Integer hJual = Integer.parseInt(scanner.nextLine());
                
                
                
                String update = "UPDATE barang SET sku = ? , nama = ?, stock = ?, harga_beli = ?, harga_jual = ? WHERE sku = ? "; 
                
                statement = connection.prepareStatement(update);
                statement.setString(1, sku);
                statement.setString(2, nama);
                statement.setInt(3, stok);
                statement.setInt(4, hBeli);
                statement.setInt(5, hJual);
                statement.setString(6, id);
                result = statement.executeUpdate();
                
                
             
            }
            else {
            	System.out.println("Data Tidak Tersedia");
            }
            
            }catch (SQLException e) {
				System.out.println(e.getMessage());
			}
                  
        return result;
    	
    	
    }
    
    public int hapus() {
    	
    	Integer result = 0;
    		
    	  try{
          
              System.out.print("SKU Barang Dihapus ? ");
              String id = scanner.nextLine();
              
              String sql = "DELETE FROM barang WHERE sku= ?";
              PreparedStatement statement = connection.prepareStatement(sql);
              statement.setString(1, id);
              result = statement.executeUpdate();
            
          }catch(SQLException e){
              System.out.println("Gagal Menghapus Data");
          }
    	  
    	  return result;
    }
    

}

