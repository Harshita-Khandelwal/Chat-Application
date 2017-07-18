import java.sql.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
public class ServerChat extends Thread
{
	private ServerSocket ss;
	private Socket socket;
	private Server s;
	private JTextArea ta;
	private DefaultListModel dlm;
	private JList l;
	ServerChat(JTextArea ta,DefaultListModel dlm,JList l)throws IOException
	{
		this.ta=ta;
		this.l=l;
		this.dlm=dlm;
		s=new Server();
		ss=s.createSS();
		Constant.i++;
	}
	public void run()
	{
		while(true)
		{
			try
			{
			Db.rs=Db.stmt.executeQuery("select max(id) as mid from chatlogin");
			Db.rs.next();
			int u=Db.rs.getInt("mid");
			//Db.rs.close();
			if(Constant.i<=u)  //i maintains how many accounts can be opened and connected to database at a tym 
			{
				System.out.println("running");
				socket=s.waitCon(ss);
				if(socket!=null)
				{
					try
					{
						ClientThread ct=new ClientThread(ta,socket,dlm,l);
						ct.start();
						//Constant.c.add(ct);
					}
					catch(IOException e)
					{
			System.out.println("G");}
				}
				Constant.i++;
			}
			}
			catch(SQLException e)
			{
				System.out.println("a");
			}
		}
	}
}