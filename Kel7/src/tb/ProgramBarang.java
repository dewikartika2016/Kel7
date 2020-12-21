package tb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramBarang {
	
	static Scanner scanner;
	static ManageBarang manageBarang;

	public static void probarang() throws SQLException {
		manageBarang = new ManageBarang();
		 scanner = new Scanner(System.in);
	        Integer option = 0;
	       
	            
	            do {
	            	System.out.println("\n=====================================");
	                System.out.println(">>>> Menu Tersedia Master Barang <<<<");
	                System.out.println("=====================================");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 1 ] Lihat Barang          ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 2 ] Tambah Barang         ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 3 ] Hapus Barang          ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 4 ] Edit Barang           ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 5 ] Cari Barang           ");
	                System.out.println("-------------------------------------");
	                System.out.println("         [ 0 ] Exit                  ");
	                System.out.println("-------------------------------------");
	                System.out.println("=====================================");
	                System.out.print  ("  Input No [ 1 / 2 / 3 / 4 / 5 / 0 ]  ");
	                
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
	                    hapusKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 4:
	                    editKoleksi();
	                    tunggu();
	                    break;
	                    
	                    case 5:
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
	      System.out.println("================================");
	      System.out.println("||      Cari Data Barang      ||");
	      System.out.println("================================");
	        ArrayList<Barang> listBarang =  manageBarang.cari();
	        
	        for( Barang barang : listBarang) {
	        	System.out.println("SKU          : " + barang.getSKU());
	        	System.out.println("Nama Barang  : " + barang.getNamaBarang());
	        	System.out.println("Stock Barang : " + barang.getStok());
	        	System.out.println("Harga Beli   : " + barang.getHargaBeli());
	        	System.out.println("Harga Jual   : " + barang.getHargaJual());
	        	
	        } 
	        
	    }
	    
	    private static void editKoleksi() throws SQLException {
	    	System.out.println("================================");
	        System.out.println("||      Edit Data Barang      ||");
	        System.out.println("================================");
	        
	        lihatKoleksi();
	        
	        if(manageBarang.edit()>0) {
	        	System.out.println("Data Berhasil Diedit");
	        }
	    
	    }
	    
	    private static void hapusKoleksi() throws SQLException{
	    	System.out.println("=================================");
	        System.out.println("||       Hapus Data Barang     ||");
	        System.out.println("=================================");
	        
	        lihatKoleksi();
	        
	      if(manageBarang.hapus()>0) {
	    	System.out.println("=====================================");
	        System.out.println("||       Data Berhasil Dihapus     ||");
	        System.out.println("=====================================");
	        }
	      
	    }
	    
	    private static void tambahKoleksi() {
	    	System.out.println("==================================");
	        System.out.println("||      Tambah Data Barang      ||");
	        System.out.println("==================================");
	        
	        
	        
	        if(manageBarang.tambahData()>0) {
	        System.out.println("================================================================================================");
	        System.out.println("||                                Data Berhasil Ditambahkan                                   ||");
	        System.out.println("================================================================================================");
	        }
	        else {
	        System.out.println("================================================================================================");
	        System.out.println("||                                     Data Gagal Dihapus                                     ||");
	        System.out.println("================================================================================================");
	        }
	        
	    }
	    
	    private static void lihatKoleksi() {
	    	System.out.println("================================================================================================");
	    	System.out.println("************************************************************************************************");
	        System.out.println("                                    Tampilkan Data Barang                                       ");
	        System.out.println("************************************************************************************************");
	        
	        ArrayList<Barang> listBarang =  manageBarang.getAll();
	        
	       
	        
	        for( Barang barang : listBarang) {
	        	String format = "SKU: %-5s | Nama Barang: %-10s | Stock: %-5s | Harga Jual: %-7s | Harga Beli: %-5s\n";
	        	System.out.printf(format, barang.getSKU(), barang.getNamaBarang(), barang.getStok(),barang.getHargaJual(), barang.getHargaBeli() );
	        } 
	    
	    }
	    
	    private static void tunggu(){
	    	System.out.println("=================================================================================================");
	        System.out.print("------------------------------------------- E N T E R  --------------------------------------------");
	        
	        scanner.nextLine();
	        
	    }

	

}

