package tb;

public class User {
	
	private String username;
	private String password;
	private String email;
	
	public User(String username, String email, String password) {
	super();
		
	    this.username = username;
		this.email = email;
		this.password = password;
	}

	User(){
		
	}
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String username) {
		username = username;
	}
	
	public String getemail() 
	{
		return email;
	}
	
	public void setemail(String email) 
	{
		this.email = email;
	}
	
	public String getpassword() {
		return password;
	}
	
	public void setpassword(String password) 
	{
		this.password = password;
	}
	
	
}
