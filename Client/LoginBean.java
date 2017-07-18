import java.sql.*;
public class LoginBean {
	private int id;
	private String name;
	private String mobile;
	private String username;
	private String password;
	private int status;
	
	LoginBean(int id,String name,String mobile,String username,String password,int status)
	{
		this.id=id;
		this.name=name;
		this.mobile=mobile;
		this.username=username;
		this.password=password;
		this.status=status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int createUser()
	{
		try
		{
			ClientDb.pstmt=ClientDb.con.prepareStatement("insert into chatlogin values(?,?,?,?,?,?)");
			ClientDb.pstmt.setInt(1,getId());
			ClientDb.pstmt.setString(2,getUsername());
			ClientDb.pstmt.setString(3,getPassword());
			ClientDb.pstmt.setString(4,getName());
			ClientDb.pstmt.setString(5,getMobile());
			ClientDb.pstmt.setInt(6,getStatus());
			ClientDb.pstmt.execute();
			ClientDb.pstmt.close();
			return 1;
		}
		catch(SQLException e)
		{
			System.out.println("Q");
			return 0;
		}
	}

}
