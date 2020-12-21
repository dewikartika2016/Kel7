package tb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Login {
	
	public static String user, pass;
	public static String kataAcak= "";

//	public static void ubahData (PreparedStatement update)throws SQLException
//		  {
//			  
//			System.out.println(kataAcak);
//			System.out.println(user);
//			update.setString(1, kataAcak);
//			update.setString(2, user);
//			update.executeUpdate();
//			
//		  }	
	
	public static void login() throws SQLException {
			
		Scanner input = new Scanner (System.in);
	
		Connection connection = null;
		connection = Koneksi.koneksiSQL();
		String sqlupdate = "UPDATE tbbpl SET password=?  where username=?";
		int salahMaksimal=0;
		
		while(true && salahMaksimal<3) 
		{
		System.out.println("=====================");
		System.out.println("=======LOG IN========");
		System.out.println("=====================");
		System.out.print("MASUKKAN USERNAME: ");
		user = input.nextLine();
		
		System.out.print("MASUKKAN PASSWORD: ");
		pass = input.nextLine();
		
		
		try 
		{
			String sql1 = "SELECT * FROM user WHERE username=? and password=?";
			PreparedStatement stmnt = connection.prepareStatement(sql1);
			stmnt.setString(1, user);
			stmnt.setString(2, pass);
			ResultSet result=stmnt.executeQuery();
			int indikatorLogin = 0;
			
			while (result.next()) 
			{
				indikatorLogin = indikatorLogin + 1;
			}
				if(indikatorLogin==1)
				{
					System.out.println("Berhasil Log in");
					String sql2 = "UPDATE user SET login_terakhir=? WHERE username=?";
					PreparedStatement pstT = connection.prepareStatement(sql2);
					Timestamp timestamp = new Timestamp(new Date().getTime());
					pstT.setTimestamp(1, timestamp);
					pstT.setString(2, user);
					pstT.executeUpdate();
					MainMenu.mainMenu();	
				}
				else if(salahMaksimal<3)
				{
					salahMaksimal=salahMaksimal+1;
					System.out.println("\nUSERNAME DAN PASSWORD SALAH "+ salahMaksimal + " KALI");
				}
			}
		
			catch(SQLException e) 
			{
			System.out.println("KONEKSI DATABASE GAGAL");
			e.printStackTrace();
			}
		
			if(salahMaksimal==3)
			{
				//GENERERATE PASSWORD RESET
				
				salahMaksimal=0;
				String characters = "QWERTYUIOPASDFGHJKLZXCVBNM123789456-=[]',./~`!@#$%^&*(";
				int length = 10;
				Random acak= new Random();
				char[] text= new char [length];
				for(int i=0; i<length; i++)
				{
					text[i]= characters.charAt(acak.nextInt(characters.length()));
				}
				for(int i=0; i<text.length; i++)
				{
					kataAcak+=text[i];
				}
				
				try 
				{
					//RESET PASSWORD
					String sqlReset = "UPDATE user SET password=? WHERE username=?";
					PreparedStatement update = connection.prepareStatement(sqlReset);
					
					System.out.println("USERNAME: "+user);
					System.out.println("PASSWORD: "+kataAcak);
					
					update.setString(1, kataAcak);
					update.setString(2, user);
					update.executeUpdate();
					System.out.println("PASSWORD TELAH DIRESET.\n");
				}
				catch (Exception e) {
					System.out.println("USERNAME TIDAK ADA");
					System.out.println("PASSWORD GAGAL DIRESET");
				}
				
			}
		}
	}
}	