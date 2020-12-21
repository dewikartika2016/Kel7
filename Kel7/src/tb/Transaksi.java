package tb;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaksi {
	
	static String kataAcak="";
	static String USERR=null, PASS=null;
	static String rsUSERNAME=null, rsPASSWORD=null;
	
	static Statement stmt;
	
	
	Scanner sc = new Scanner(System.in);
	User userInput= new User();
	
	String noresi,username,tanggal;
	
	//Menu Data
    public void Menu() throws SQLException {
    	boolean ulang=true;
    	do 
    	{
	        System.out.println("\n--MENU DATA TRANSAKSI--");
	        System.out.println("1. Save Data Transaksi");
	        System.out.println("2. Update Data Transaksi");
	        System.out.println("0. Kembali");
	        System.out.print("Masukkan Pilihan : ");
	
	        try {
	            String pilih = sc.next();
	            switch (pilih) {
	                case "0":
	                    MainMenu.mainMenu();
	                    ulang=false;
	                    break;
	                case "1":
	                    SaveData();
	                    ulang=false;
	                    break;
	                case "2":
	                	UpdateData();
	                    ulang=false;
	                    break;
	                default:
	                    System.out.println("Pilihan yang anda masukkan tidak valid!");
	            }
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
    	}while(ulang);
    }

	public void SaveData() throws Exception {
	try {
		Connection c= Koneksi.koneksiSQL();
		int i=1;
		boolean ulang=true;
		String NORESI="";
		//Class.forName("com.mysql.cj.jdbc.Driver");
		DataTransaksi trs = new DataTransaksi();
		stmt = c.createStatement();
		PreparedStatement insert;
		
		trs.noresi();
		trs.Tanggal();
		
		while(ulang)
		{
		System.out.println(" ------------------------------------------------------- ");
		System.out.println("|           >> MENU  TRANSAKSI <<              |");
		System.out.println(" ------------------------------------------------------- \n");
		
		System.out.println("SEMUA DATA BARANG:");
		ResultSet rs = stmt.executeQuery("SELECT sku, nama, stock FROM barang");
		
			while(rs.next()) 
			{ //Perbaris
			String rsSKU = rs.getString("sku");
			String rsNAMA = rs.getString("nama");
			String rsSTOCK = rs.getString("stock");
			
			System.out.println("SKU	: " + rsSKU);
			System.out.println("NAMA	: " + rsNAMA);
			System.out.println("STOK	: " + rsSTOCK);
			System.out.println("\n");
			
			}
		
		if(i==1)
		{
             String sqlinsert1 = "INSERT INTO transaksi (noresi, tanggal, username) "
			  		+ "VALUES (?, ?, ?)";
            insert = c.prepareStatement(sqlinsert1);
            insert.setString(1, trs.NoresiT);
     	    insert.setString(2, trs.TanggalT);
     	    insert.setString(3, Login.rsUSERNAME);
     	    insert.executeUpdate();
     	    i++;
		}
		trs.DTransaksi();
		
		System.out.println("\nPERUBAHAN DATA :");
		rs = stmt.executeQuery("SELECT sku, nama, stock FROM barang");
		while(rs.next()) 
		{ //Perbaris
		String rsSKU = rs.getString("sku");
		String rsNAMA = rs.getString("nama");
		String rsSTOCK = rs.getString("stock");
		
		System.out.println("SKU	: " + rsSKU);
		System.out.println("NAMA	: " + rsNAMA);
		System.out.println("STOK	: " + rsSTOCK);
		System.out.println("\n");
		}
		System.out.println(trs.NoresiT+trs.pilihSKU);
		rs = stmt.executeQuery("SELECT id, jumlah FROM transaksi_detail WHERE noresi= '" + trs.NoresiT + "' AND sku= '" + trs.pilihSKU + "';"); 
		
		if(rs.next()) 
		{ //Perbaris
			 int id = rs.getInt("id");
			 System.out.println(trs.NoresiT+"|"+trs.pilihSKU+"|"+id);
			 String sqlupdate = "UPDATE transaksi_detail SET  jumlah=? where id=?";
			 PreparedStatement update = c.prepareStatement(sqlupdate);
			 stmt = c.createStatement();
			 update.setInt(1, trs.jumSKU);
			 update.setInt(2, id);
		}
		else
		{
	     	String sqlinsert2 = "INSERT INTO transaksi_detail (sku, noresi, jumlah, harga) "+ "VALUES (?, ?, ?, ?)";
	     	insert = c.prepareStatement(sqlinsert2);
	        insert.setString(1, trs.pilihSKU);
	   	    insert.setString(2, trs.NoresiT);
	   	    insert.setInt(3, trs.jumSKU);
	   	    insert.setInt(4, trs.hargaJSKU);
	   	    insert.executeUpdate();
		}
            System.out.println("\nData Berhasil Tersimpan");
            
            boolean tanya=true;
            while(tanya)
            {
	            System.out.print("APAKAH ADA BARANG YANG AKAN DI INPUTKAN LAGI(Y/N)?->");
	            String jawab= sc.next().toUpperCase();
	            switch(jawab)
	            {
	            case "Y":tanya=false;
	            	break;
	            case "N":
	            	tanya=false;
	            	ulang=false;
	            	break;
	            	default:
	            		System.out.println("MASUKKAN Y/N!");
	            }
            }
		}
        } 
        catch (Exception e) {
        	System.err.println("Terjadi error : " + e.toString());
        }   
		Menu();
	}

	public void UpdateData() throws Exception {
		
		 Connection c= Koneksi.koneksiSQL();
		String ambilSKU=null;
		int ambilJUMLAH=0,ambilHARGA=0;
		
		ResultSet rs = stmt.executeQuery("SELECT id, noresi, sku, jumlah, harga FROM transaksi_detail");
		System.out.println("\nSEMUA DATA TRANSAKSI DETAIL:");
		System.out.println(" _______________________________________________________________________________________________");
		System.out.println("|	NO	|	ID	|	NO RESI	|	SKU	|	JUMLAH	|	HARGA	|");
		System.out.println(" _______________________________________________________________________________________________");
		int i=1;
		while(rs.next()) 
		{ //Perbaris
		 int rsID= rs.getInt("id");
		 String rsNORESI = rs.getString("noresi");
		 String rsSKU = rs.getString("sku");
		 int rsJUMLAH= rs.getInt("jumlah");
		 int rsHARGA = rs.getInt("harga");
		System.out.println("|	"+"["+i+"]"+"	|	"+rsID+"	|	"+rsNORESI+"	|	"+rsSKU+"	|	"+rsJUMLAH+"	|	"+rsHARGA+"	|");
		System.out.println(" _______________________________________________________________________________________________");
		i++;
		}
		
		System.out.print("\nMASUKKAN NO RESI: ");
		String inHuruf= sc.next();
		rs = stmt.executeQuery("SELECT id, sku, jumlah, harga FROM transaksi_detail WHERE noresi= '" + inHuruf + "';"); 
		System.out.println("DATA TRANSAKSI DETAIL BERDASARKAN NO RESI:");
		System.out.println(" _______________________________________________________________________________");
		System.out.println("|	NO	|	ID	|	SKU	|	JUMLAH	|	HARGA	|");
		System.out.println(" _______________________________________________________________________________");
		i=1;
		while(rs.next()) 
		{ //Perbaris
		 int rsID= rs.getInt("id");
		 String rsSKU = rs.getString("sku");
		 int rsJUMLAH= rs.getInt("jumlah");
		 int rsHARGA = rs.getInt("harga");
		System.out.println("|	"+"["+i+"]"+"	|	"+rsID+"	|	"+rsSKU+"	|	"+rsJUMLAH+"	|	"+rsHARGA+"	|");
		System.out.println(" _______________________________________________________________________________");
		i++;
		}
		
		System.out.print("\nMASUKKAN NO ID: ");
		int inAngka= sc.nextInt();
		rs = stmt.executeQuery("SELECT sku, jumlah, harga FROM transaksi_detail WHERE id= '" + inAngka + "';"); 
		i=1;
		if(rs.next()) 
		{ 
			String rsSKU = ambilSKU = rs.getString("sku");
			int rsJUMLAH= ambilJUMLAH = rs.getInt("jumlah");
			int rsHARGA = ambilHARGA = rs.getInt("harga");
			System.out.println("DATA TRANSAKSI DETAIL BERDASARKAN NO ID:");
			System.out.println(" ______________________________________________________________");
			System.out.println("|	NO	|	SKU	|	JUMLAH	|	HARGA	|");
			System.out.println(" ______________________________________________________________");
			System.out.println("|	"+"["+i+"]"+"	|	"+rsSKU+"	|	"+rsJUMLAH+"	|	"+rsHARGA+"	|");
			System.out.println(" _______________________________________________________________");
			i++;
		
		//MENGEMBALIKAN STOK DARI TRANSAKSI DETAIL
		rs= stmt.executeQuery("SELECT stock, harga_jual FROM barang WHERE sku= '" + ambilSKU + "';"); 
		if(rs.next()) 
		{ 
		 rsJUMLAH= rs.getInt("stock");
		 int rshrgJUAL= rs.getInt("harga_jual");
		 ambilJUMLAH=ambilJUMLAH+rsJUMLAH;
		}
		String sqlupdate = "UPDATE barang SET  stock=? WHERE sku=?";
		PreparedStatement update = c.prepareStatement(sqlupdate);
				 update.setInt(1, ambilJUMLAH);
				 update.setString(2, ambilSKU);
				 update.executeUpdate();
				 
				//TAMPILAN BARANG
				 	i=1;
					rs= stmt.executeQuery("SELECT * FROM barang"); 
					System.out.println("SEMUA DATA BARANG:");
					System.out.println(" _______________________________________________________________________________________________");
					System.out.println("|	NO	|	SKU	|	NAMA	|	STOCK	|   HARGA_JUAL  |   HARGA_BELI  |");
					System.out.println(" _______________________________________________________________________________________________");
					while(rs.next()) 
					{ 
						rsJUMLAH= rs.getInt("stock");
						rsSKU= rs.getString("sku");
						String rsNAMA= rs.getString("nama");
						int rsHrgJUAL= rs.getInt("harga_jual");
						int rsHrgBELI= rs.getInt("harga_beli");
						
						System.out.println("|	"+"["+i+"]"+"	|	"+rsSKU+"	|	"+rsNAMA+"	|	"+rsJUMLAH+"	|	"+rsHrgJUAL+"	|	"+rsHrgBELI+"	|");
						System.out.println(" _______________________________________________________________________________________________");
					}
						System.out.print("MASUKKAN SKU			: ");
						ambilSKU= sc.next();
						System.out.print("MASUKKAN JUMLAH PRODUK		: ");
						ambilJUMLAH=sc.nextInt();
						rs = stmt.executeQuery("SELECT harga_jual,stock FROM barang WHERE sku= '" + ambilSKU + "';"); 
						i=1;
						if(rs.next()) 
						{ 
							rsHARGA = rs.getInt("harga_jual");
							rsHARGA=rsHARGA*ambilJUMLAH;
							int rsSTOK= rs.getInt("stock");
							rsSTOK=rsSTOK-ambilJUMLAH;
							
							sqlupdate = "UPDATE barang SET stock=? WHERE sku=?";
							update = c.prepareStatement(sqlupdate);
							update.setInt(1, rsSTOK);
							update.setString(2, ambilSKU);
							update.executeUpdate();
							
							sqlupdate = "UPDATE transaksi_detail SET sku=?,jumlah=?,harga=? WHERE id=?";
							update = c.prepareStatement(sqlupdate);
							update.setString(1, ambilSKU);
							update.setInt(2, ambilJUMLAH);
							update.setInt(3, rsHARGA);
							update.setInt(4, inAngka);
							update.executeUpdate();
							
							
							rs = stmt.executeQuery("SELECT id, noresi, sku, jumlah, harga FROM transaksi_detail");
							System.out.println("\nSEMUA DATA TRANSAKSI DETAIL:");
							System.out.println(" _______________________________________________________________________________________________");
							System.out.println("|	NO	|	ID	|	NO RESI	|	SKU	|	JUMLAH	|	HARGA	|");
							System.out.println(" _______________________________________________________________________________________________");
							i=1;
							while(rs.next()) 
							{ //Perbaris
							 int rsID= rs.getInt("id");
							 String rsNORESI = rs.getString("noresi");
							 rsSKU = rs.getString("sku");
							 rsJUMLAH= rs.getInt("jumlah");
							 rsHARGA = rs.getInt("harga");
							System.out.println("|	"+"["+i+"]"+"	|	"+rsID+"	|	"+rsNORESI+"	|	"+rsSKU+"	|	"+rsJUMLAH+"	|	"+rsHARGA+"	|");
							System.out.println(" _______________________________________________________________________________________________");
							i++;
							}
						}	
		}	
	}
}