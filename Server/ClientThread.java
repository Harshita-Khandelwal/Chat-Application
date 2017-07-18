import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
public class ClientThread extends Thread
{
	private InputStream sin;
	private OutputStream sout;
	private BufferedReader in;
	private PrintWriter out;
	private String line,uname,msg;
	private Socket socket;
	private JTextArea ta;
	public int j,id,n,dsize;
	private DefaultListModel dlm;
	private JList  l;
	ClientThread(JTextArea ta,Socket socket,DefaultListModel dlm,JList l) throws IOException
	{
			this.socket=socket;
			this.ta=ta;
			String clientaddr;
			int clientport;
			InetAddress ina;
			ina=socket.getInetAddress();
			clientaddr=ina.getHostAddress();
			clientport=socket.getPort();
			System.out.println("cladd:"+clientaddr);
			System.out.println("clprt:"+clientport);
			Socket o;
			try
			{
				Thread.sleep(1000);
				Db.rs=Db.stmt.executeQuery("select * from storesocket");
				while(Db.rs.next())
				{
					String addr;
					int port;
					addr=Db.rs.getString("addr");
					port=Db.rs.getInt("port");
					System.out.println("add:"+addr);
					System.out.println("prt:"+port);
					if(clientaddr.equals(addr))
					{
						if(clientport==port)
						{
							id=Db.rs.getInt("id");
						}
					}
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("b");
				ex.printStackTrace();
			}
			this.j=id;
			n=Constant.r;
			//Constant.o[0][Constant.r]=j;   these things commented here are those which were used when 2D array of Object was used
			//Constant.o[1][Constant.r]=socket;
			Constant.index.add(j);
			Constant.socketObj.add(socket);
			Constant.r++;
			this.dlm=dlm;
			this.l=l;
			try
			{
				Db.rs=Db.stmt.executeQuery("select username from chatlogin where id="+j);
				Db.rs.next();
				uname=Db.rs.getString("username");
				dlm.addElement(uname);
			}
			catch(Exception e)
			{
				System.out.println("c");
				e.printStackTrace();
			}
			sin=socket.getInputStream();
			sout=socket.getOutputStream();
			in=new BufferedReader(new InputStreamReader(sin));
			out=new PrintWriter(sout);
	}
		public void run()
		{	try
			{	
			while(true)
			{	
				line=in.readLine();
				System.out.println("line received is:"+line);
				if((line==null) | (line.equals("")))  //THIS CONDITION IS NECESSARY (HERE AND AT LINE63 AND IN CLIENTGUI ALSO) OTHERWISE DUE TO WHILE LOOP \n PRINTS CONTINUOUSLY CAUSING WIDE GAP
				{
				}
				else
				{
					if(line.equals("kkll$j5jhh8onlinellk2kgg2ddd"))
					{
						try
						{
							Db.pstmt=Db.con.prepareStatement("update time set msg=? where user=\""+uname+"\"");
							Db.pstmt.setString(1,"online");
							Db.pstmt.execute();
							Db.pstmt.close();
						}
						catch(SQLException ex)
						{
							System.out.println("TR");
							ex.printStackTrace();
						}
						dsize=dlm.getSize();
						msg=line;
						if(dsize!=0)
						{
							String last=(String)dlm.lastElement();
							int ind=dlm.indexOf(last);
							for(int y=0;y<ind+1;y++)
							{
								String s=(String)dlm.get(y);
								if(s.equals(uname))
								{
								System.out.println(uname+"is online dont send it to"+s);
								}
								else
								{
								System.out.println(uname+"is online send it to"+s);
									msg=line;
									sendData(s,msg);	
									line="kkll$j5jhh8onlinellk2kgg2ddd";
								}
							}
						}
					}
					else if(line.equals("kkll$j5jhh8focusllk2kgg2ddd"))
					{
						java.sql.Time d=new java.sql.Time(System.currentTimeMillis());
						String time=d.toString();
						try
						{
							Db.pstmt=Db.con.prepareStatement("update time set msg=? where user=\""+uname+"\"");
							Db.pstmt.setString(1,time);
							Db.pstmt.execute();
							Db.pstmt.close();
						}
						catch(SQLException ex)
						{
							System.out.println("YR");
							ex.printStackTrace();
						}
						dsize=dlm.getSize();
						if(dsize!=0)
						{
							String last=(String)dlm.lastElement();
							int ind=dlm.indexOf(last);
							for(int y=0;y<ind+1;y++)
							{
								String s=(String)dlm.get(y);
								if(s.equals(uname))
								{
									
								System.out.println(uname+"is offline dont send it to"+s);
									}
								else
								{
								
								System.out.println(uname+"is offline send it to"+s);
									line=line+time;	
									msg=line;
									sendData(s,msg);
									line="kkll$j5jhh8focusllk2kgg2ddd";
								}
							}
						}
					}
					else
					{
						int sindex=line.indexOf("Q!2%:");
						int lindex=line.lastIndexOf("--YU86%");
						String rname=line.substring(sindex+5,lindex);
						System.out.println("receiver:"+rname);
						line=line.substring(lindex+7);
						sendData(rname,line);
					}
					}
				}
			}
			catch(Exception e)//when client closes or logout connection reset exception occurs here n now status etc things are updated
			{	
				Constant.i--;
				System.out.println("on closing i="+Constant.i);
				//e.printStackTrace();
				try
				{
					Db.stmt.executeUpdate("update chatlogin set status=0 where id="+j);
					Db.stmt.execute("delete from storesocket where id="+j);	
					Db.stmt.execute("delete from time where user=\""+uname+"\"");
					dlm.removeElement(uname);
				//	socket=null;
					//Constant.o[1][n]=socket;
					Constant.index.remove(n);
					Constant.socketObj.remove(n);
				}
				catch(SQLException ex)
				{
					System.out.println("D");
					ex.printStackTrace();
				}
			}
		}
		public void serverData(String line)
		{
			System.out.println(line);
			out.println(line);
			out.flush();
		}
		public void sendData(String rname,String line)
		{
			try
			{
				Db.rs=Db.stmt1.executeQuery("select id from chatlogin where username='"+rname+"'");
				Db.rs.next();
				int k=Db.rs.getInt("id");
				if((line==null) | (line.equals("")))
				{
				}
				else
				{
					ta.append("\nServer:"+line);
				}	
				//for(int w=0;w<Constant.r;w++)
				//{
				Iterator itr=Constant.index.iterator();
					while(itr.hasNext()==true)
					{
						//int r=(Integer)Constant.o[0][w];
						int r=(Integer)itr.next();
						if(r==k)
						{
							try
							{
								//Socket socket=(Socket)Constant.o[1][w];
								int ii=Constant.index.indexOf(r);
								Socket socket=(Socket)Constant.socketObj.get(ii);
								if(socket!=null)
								{
									InputStream sin=socket.getInputStream();
									OutputStream sout=socket.getOutputStream();
									BufferedReader in=new BufferedReader(new InputStreamReader(sin));
									PrintWriter out=new PrintWriter(sout);
									System.out.println("line1"+line);
									String l=line;
									l="Q!2%:"+uname+"--YU86%"+l;
									System.out.println("line2"+l);
									out.println(l);
									out.flush();
									break;
								}
							}
							catch(Exception e)
							{
								System.out.println("pp");
								e.printStackTrace();
							}
						}
					}
				//	}
				}
				catch(SQLException ex)
				{
					System.out.println("I");
					ex.printStackTrace();
				}
		}
}