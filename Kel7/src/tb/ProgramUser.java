package tb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramUser {
	
	static Scanner scanner;
	static ManageUser ManageUser;

	public static void main(String[] args) throws SQLException {
		
		ManageUser = new ManageUser();
		 scanner = new Scanner(System.in);
	        Integer option = 0;
	       
	            
	            do {
	            	System.out.println("=====================================");
	                System.out.println("      Menu Tersedia User Control     ");
	                System.out.println("=====================================");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 1 ] Lihat User            ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 2 ] Tambah User           ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 3 ] Edit User             ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 4 ] Cari User             ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 0 ] Exit                  ");
	                System.out.println("-------------------------------------");
	                System.out.println("=====================================");
	                System.out.print  ("  Input No [ 1 / 2 / 3 / 4/ 0 ]  ");
	                
	                option = Integer.parseInt(scanner.nextLine());
	                
	                switch (option) {
	                    
	                    case 1:
	                    lihatKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 2:
	                    tambahKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 3:
	                    editKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 4:
	                    cariKoleksi();
	                    tunggu();
	                    break;
	                   
	                    case 0:
	                    break;
	                    default:
	                    System.out.println("========================================");
	                    System.out.println("||       Inputan Data Tidak Valid     ||");
	                    System.out.println("========================================");
	                }
	                
	                
	            } while (option != 0);
	         
	    }
	    
	    private static void cariKoleksi() throws SQLException {
	      System.out.println("\n================================");
	      System.out.println("||         Cari User          ||");
	      System.out.println("================================");
	        ArrayList<User> ListUser =  ManageUser.cari();
	        
	        for( User user : ListUser) {
	        	System.out.println("Username \t: " + user.getusername());
	        	System.out.println("Login Terakhir \t: " + user.getlogin());
	        	System.out.println("Email \t\t: " + user.getemail());
	        	System.out.println("Password \t: " + user.getpassword());
	        } 
	        
	    }
	    
	    private static void editKoleksi() throws SQLException {
	    	System.out.println("\n================================");
	        System.out.println("||          Edit User         ||");
	        System.out.println("================================");
	        
	        lihatKoleksi();
	        
	        if(ManageUser.edit()>0) {
	        	System.out.println("User Berhasil Diedit");
	        }
	    
	    }
	    
	    private static void tambahKoleksi() {
	    	System.out.println("\n==================================");
	        System.out.println("||          Tambah User         ||");
	        System.out.println("==================================");
	        
	        
	        
	        if(ManageUser.tambahData()>0) {
	        	System.out.println("=========================================");
	        	System.out.println("||       User Berhasil Ditambahkan     ||");
	        	System.out.println("=========================================");
	        }
	        else {
	        	System.out.println("==================================");
	        	System.out.println("||      User Gagal Ditambah     ||");
	        	System.out.println("==================================");
	        }
	        
	    }
	    
	    private static void lihatKoleksi() throws SQLException {
	    	System.out.println("\n=====================================");
	        System.out.println("||         Tampilkan User          ||");
	        System.out.println("=====================================");
	        
	        ArrayList<User> ListUser =  ManageUser.getAll();
	        
	       
	        
	        for( User user : ListUser) {
	        	System.out.println("\nUsername \t: " + user.getusername());
	        	System.out.println("Login Terakhir \t: " + user.getlogin());
	        	System.out.println("Email \t\t: " + user.getemail());
	        	System.out.println("Password \t: " + user.getpassword());
	        	
	        } 
	    
	    }
	    
	    private static void tunggu(){
	        System.out.print("       \nTekan Enter untuk Next    ");
	        
	        scanner.nextLine();
	        
	    }

	

}

