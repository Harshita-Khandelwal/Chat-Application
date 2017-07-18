import java.sql.*;
class Db
{
	public static Connection con;
	public static Statement stmt,stmt1;
	public static ResultSet rs; 
	public static PreparedStatement pstmt;
	static
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MyChat","root","admin");
		}
		catch(SQLException ex)
		{
			System.out.println("K");
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","admin");
				stmt=con.createStatement();
				stmt.execute("create database MyChat");
				stmt.close();
				con.close();
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MyChat","root","admin");
			}
			catch(SQLException e)
			{
			System.out.println("L");
			e.printStackTrace();
			}
		}
		try
		{
			stmt=con.createStatement();
			stmt1=con.createStatement();
			try
			{
				//stmt.execute("create table chatlogin(id smallint,username varchar(10),password varchar(10),name varchar(20),MobileNo varchar(10),status smallint,primary key(id),unique(username))");
				//stmt.execute("create table storesocket(id smallint,addr varchar(20),port mediumint,primary key(id))");
				//stmt.execute("create table time(id smallint auto_increment,user varchar(10),msg varchar(10),primary key(id))");
				stmt.execute("create table status(st varchar(10))");
				stmt.execute("insert into status values('false')");
			}
			catch(SQLException r)
			{
			System.out.println("M");
			System.out.println(r.getMessage());
			
			}
		}
		catch(SQLException exc)
		{
			System.out.println("N");
			exc.printStackTrace();
		}
	}
	public void finalize()
	{
		try
		{
			rs.close();
			stmt.close();
			stmt1.close();
			con.close();
		}
		catch(SQLException e)
		{
		}
	}
}