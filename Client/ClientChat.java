import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class ClientChat
{
	private Socket socket;
	private String line;
	private InputStream sin;
	private OutputStream sout;
	private BufferedReader in;
	private PrintWriter out;
	private JFrame jf;
		ClientChat(JFrame jf,JList l,JDesktopPane desktop)throws IOException
		{
			this.jf=jf;
			Client c=new Client();
			socket=c.getCon();
			
			sin=socket.getInputStream();
			sout=socket.getOutputStream();
			in=new BufferedReader(new InputStreamReader(sin));
			out=new PrintWriter(sout);
			ClientRec r=new ClientRec(in,jf,socket,l,desktop);
			r.start();
		}
		public PrintWriter makeStream()throws IOException
		{
			return out;
		}
}