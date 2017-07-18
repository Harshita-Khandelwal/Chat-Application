import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
class Client 
{
	private Socket socket;
	Client()
	{
		
	}
	
	public Socket getCon()
	{
		int client_port,server_port=6666;
		String clientaddr,address="127.0.0.1";
		InetAddress ina;
		try
		{
			InetAddress ipAddress=InetAddress.getByName(address);
			System.out.println("A socket with ip address"+address+"and port"+server_port+"\n");
			Socket socket=new Socket(ipAddress,server_port);
			System.out.println("Just got the hold of the program \n");
			System.out.println("port"+socket.getLocalPort());
			System.out.println("addr"+socket.getLocalAddress());
			
			client_port=socket.getLocalPort();
			ina=socket.getLocalAddress();
			clientaddr=ina.getHostAddress();
			
			ClientDb.pstmt=ClientDb.con.prepareStatement("insert into storesocket values(?,?,?)");
			ClientDb.pstmt.setInt(1,Login.storelogin);
			ClientDb.pstmt.setString(2,clientaddr);
			ClientDb.pstmt.setInt(3,client_port);
			ClientDb.pstmt.execute();
			ClientDb.pstmt.close();
			Login.storelogin=0;
			
			return socket;
		}
		catch(Exception e)
		{
			System.out.println("A");
			e.printStackTrace();
		}
		return null;
	}
}
