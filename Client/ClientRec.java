import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.sql.*;
public class ClientRec extends Thread
{
	private BufferedReader in;
	private String line;
	private JFrame jf;
	private Socket socket;
	private int count,c;
	private JTextArea ta;
	private JList l;
	private JDesktopPane desktop;
	ClientRec(BufferedReader in,JFrame jf,Socket socket,JList l,JDesktopPane desktop) throws IOException
	{
			this.in=in;
			this.socket=socket;
			this.jf=jf;
			this.l=l;
			this.desktop=desktop;
					System.out.println("constr");
			
	}
		public void run()
		{	try
			{	
			while(true)
			{	
				line=in.readLine();
					System.out.println("hehe line:"+line);
				if((line==null) | (line.equals("")))  //THIS CONDITION IS NECESSARY (HERE AND AT LINE63 AND IN CLIENTGUI ALSO) OTHERWISE DUE TO WHILE LOOP \n PRINTS CONTINUOUSLY CAUSING WIDE GAP
				{
				}
				else
				{
					int sindex=line.indexOf("Q!2%:");
					int lindex=line.lastIndexOf("--YU86%");
					String sname=line.substring(sindex+5,lindex);
					System.out.println("name:"+sname);
					count=0;
					line=line.substring(lindex+7);
					Iterator itr=ConstClient.userName.iterator();
					while(itr.hasNext()==true)
					{
						String s1=(String)itr.next();
						if(sname.equals(s1))
						{
							count=1;
							int i=ConstClient.userName.indexOf(sname);
							int length=line.length();
							if(length>26)
							{
								String li=line.substring(0,27);
								if(li.equals("kkll$j5jhh8focusllk2kgg2ddd"))
								{
									System.out.println("Last Seen Updation");
									JInternalFrame j=(JInternalFrame)ConstClient.internalFrame.get(i);
									
									JLabel ll=new JLabel(sname);
									ll.setFont(new Font("Comic Sans MS",Font.BOLD,20));
									ll.setForeground(new Color(255,0,0));
								
									String time=line.substring(27);
									System.out.println("time:"+time);
									JLabel ll2=new JLabel("Last Seen:"+time);
									
									JPanel r2=(JPanel)ConstClient.panel.get(i);
									r2.removeAll();
									r2.revalidate();
									r2.repaint();
									r2.add(ll);
									r2.add(ll2);
									System.out.println("label:"+ll2.getText());
									
									ConstClient.panel.set(i,r2);
									j.validate();
									jf.validate();
									ta=null;
								}
								else if(line.equals("kkll$j5jhh8onlinellk2kgg2ddd"))
								{
									System.out.println("Online Updation");
									JInternalFrame j=(JInternalFrame)ConstClient.internalFrame.get(i);
								
									JLabel ll=new JLabel(sname);
									ll.setFont(new Font("Comic Sans MS",Font.BOLD,20));
									ll.setForeground(new Color(255,0,0));
								
									JLabel ll2=new JLabel("Online");
									System.out.println("label:"+ll2.getText());
									
									JPanel r2=(JPanel)ConstClient.panel.get(i);
									r2.removeAll();
									r2.revalidate();
									r2.repaint();
									r2.add(ll);
									r2.add(ll2);
									
									ConstClient.panel.set(i,r2);
									j.validate();
									jf.validate();
									ta=null;
								}
							}
							else 
							{
								JTextArea t=(JTextArea)ConstClient.textArea.get(i);
								ta=t;
								System.out.println("A");
							}
							break;
						}
					}
					if(count==0)
					{
					
							System.out.println("B");
						JInternalFrame jif=new JInternalFrame();
						jif.setSize(300,350);
						jif.setVisible(true);
						JTextArea tt=new JTextArea(21,25);
						tt.setEditable(false);
						JScrollPane sp=new JScrollPane(tt);

						JPanel r=new JPanel();
						r.setLayout(new BorderLayout());
						r.add(sp,"Center");
						((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI()).setNorthPane(null);
						
						JPanel r1=new JPanel();
						r1.setLayout(new FlowLayout());
									
						JLabel ll=new JLabel(sname);
						ll.setFont(new Font("Comic Sans MS",Font.BOLD,20));
						ll.setForeground(new Color(255,0,0));
						r1.add(ll);
						
						String li=line.substring(0,27);
						if(li.equals("kkll$j5jhh8focusllk2kgg2ddd"))
							{
								System.out.println("Last Seen Updation");
								String time=line.substring(27);
								System.out.println("time:"+time);
								JLabel ll2=new JLabel("Last Seen:"+time);
								System.out.println("newly created jif label:"+ll2.getText());
								r1.add(ll2);
								ta=null;
							}
							else if(line.equals("kkll$j5jhh8onlinellk2kgg2ddd"))
							{
								System.out.println("Online Updation");
								
								JLabel ll2=new JLabel("Online");
								System.out.println("newly created jif label:"+ll2.getText());
								r1.add(ll2);
								ta=null;
							}
							else
							{
								ta=tt;
							}
							jif.add(r1,BorderLayout.NORTH);
													
						jif.add(r,BorderLayout.CENTER);
						desktop.add(jif);
						
						ConstClient.userName.add(sname);
						ConstClient.internalFrame.add(jif);
						ConstClient.textArea.add(tt);
						ConstClient.panel.add(r1);
						Iterator it=ConstClient.userName.iterator();
						while(it.hasNext())
						{
							System.out.println("userName:"+it.next());
						}
						EnabledListSelectionModel elsm=new EnabledListSelectionModel();
						l.setSelectionModel(elsm);
						if(c==0)
							l.setSelectedIndex(0);
					}
					if(ta!=null)	
						{
							ta.append("\n"+sname+":"+line);
							System.out.println("line:"+line);
							line="";
						}
				}
			}
			}
			catch(IOException e)
			{	
			System.out.println("C");//Connection Reset exception when server is closed 
				jf.setVisible(false);
				jf.dispose();
				try
				{
					socket.close();
				}
				catch(IOException ex)
				{}
				try
				{
					ClientDb.stmt.executeUpdate("update chatlogin set status=0");
					ClientDb.stmt.execute("delete from storesocket");	
					ClientDb.stmt.execute("delete from time");	
					ClientDb.stmt.execute("update status set st='false'");	
				}
				catch(SQLException er)
				{}
				JOptionPane.showMessageDialog(jf,"Server shut down!!","Message",JOptionPane.ERROR_MESSAGE);
				Login l=new Login();
				//e.printStackTrace();
			}
		}
}	