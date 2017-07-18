import java.net.*;
class Server
{
	private ServerSocket ss;
	
	Server()
	{
	}
	public ServerSocket createSS()
	{
		int port=6666;
		try
		{
			ss=new ServerSocket(port);
			return ss;
		}
		catch(Exception e)
		{
			System.out.println("E");
			e.printStackTrace();
		}
		return null;
	}
	public Socket waitCon(ServerSocket ss)
	{
		try
		{
			System.out.println("Waiting for a client \n");
			Socket socket=ss.accept();
			System.out.println("Client connected");
			return socket;
		}
		catch(Exception e)
		{
			System.out.println("F");
			e.printStackTrace();
		}
		return null;
	}
}