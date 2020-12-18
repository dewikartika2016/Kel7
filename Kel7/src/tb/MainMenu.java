package tb;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	private static void pilihanMenu(int menuPilihan) throws SQLException {
	    switch(menuPilihan) {
	        case 1://masterBarang
	            break;
	        case 2:
	            break;
	        case 3:
	            break;
	        case 4://transaksi
	            break;
	        case 5:
	            break;
	        case 6:
	        	Login.login();
	            break;
	        default:
				System.out.println("Pilihan Anda Tidak Tersedia");
	    }
	}
	public static Scanner input =new Scanner(System.in);
	
	public static void mainMenu() throws SQLException {
//		String a;
//		do {​​
//			do {​​
//			a="Y";
	    int loopX = 0;
	    int menuPilihan = 0;
	    while(loopX == 0) 
	    {
		System.out.println("MENU:");
		System.out.println("1. PENGELOLAAN USER");
		System.out.println("2. PENGELOLAAN DATA MASTER BARANG");  //kalau bisa gabungin master barang dengan restock
		System.out.println("3. PENGELOLAAN RESTOK BARANG");
		System.out.println("4. PENGELOLAAN TRANSAKSI PENJUALAN BARANG");
		System.out.println("5. LAPORAN ");
		System.out.println("6. LOG OUT");
		System.out.print("Masukkan Pilihan : [1]/[2]/[3]/[4]/[5]/[6] :");
        try {
            menuPilihan = input.nextInt();
            loopX = 1;
            if(menuPilihan<1 || menuPilihan>6)
            {
            	System.out.println("Masukkan Angka 1-6!");
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Harus Memasukkan Angka!");
        }
    }
    System.out.println("===============================\n");
    pilihanMenu(menuPilihan);
	}
}