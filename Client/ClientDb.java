import java.sql.*;
class ClientDb
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
			System.out.println("M");
		}
		try
		{
			stmt=con.createStatement();
			stmt1=con.createStatement();
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