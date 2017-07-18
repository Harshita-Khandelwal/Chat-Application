import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
public class ClientGui implements ActionListener,WindowListener
{
	private JFrame jf;
	private JTextArea ta;
	private JTextField tf;
	private JScrollPane sp,spl;
	private DefaultListModel dlm;
	private JList l;
	private	JPanel p,p1,p2,p3,p4;
	private JLabel label;
	private JButton send,logout;
	private String line;
	private PrintWriter out;
	private int id,c;
	private String user;
	private JDesktopPane desktop;
	private Thread t1;
	private Vector v;
	ClientGui()
	{
		jf=new JFrame();
		id=Login.storelogin;
		try
		{
			ClientDb.rs=ClientDb.stmt.executeQuery("select username from chatlogin where id="+id);
			ClientDb.rs.next();
			user=ClientDb.rs.getString("username");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		jf.setSize(500,480);
		jf.setLocation(200,200);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ConstClient.userName=new Vector();
		ConstClient.internalFrame=new Vector();
		ConstClient.textArea=new Vector();
		ConstClient.panel=new Vector();
		
		String on="online";
		try
		{
			ClientDb.pstmt=ClientDb.con.prepareStatement("insert into time values(null,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ClientDb.pstmt.setString(1,user);
			ClientDb.pstmt.setString(2,on);
			ClientDb.pstmt.execute();
			ClientDb.pstmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("ttr");
			e.printStackTrace();
		}
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		ta=new JTextArea(21,25);
		send=new JButton("send");
		logout=new JButton("Logout");
		logout.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											JOptionPane.showMessageDialog(jf,"Logout Successfully!!","Message",JOptionPane.ERROR_MESSAGE);
											jf.setVisible(false);
											jf.dispose();
											System.exit(1);  //so at server side exception occurs where now status is set to 0 again and other working is also done
										}
									});
		sp=new JScrollPane(ta);
		tf=new JTextField(30);
		tf.addKeyListener(new KeyListener()
							{
								public void keyTyped(KeyEvent e)
								{}
								public void keyReleased(KeyEvent e)
								{}
								public void keyPressed(KeyEvent e)
								{
									int x=e.getKeyCode();
									if(x==KeyEvent.VK_ENTER)
									{
										ActionEvent ae=new ActionEvent(send,ActionEvent.ACTION_PERFORMED,"send");
										actionPerformed(ae);
									}
								}
							});
		
		dlm=new DefaultListModel();
		l=new JList(dlm);
		spl=new JScrollPane(l);
		DisabledListSelectionModel dlsm=new DisabledListSelectionModel();
		l.setSelectionModel(dlsm);
		l.addListSelectionListener(new ListSelectionListener()
									{
										public void valueChanged(ListSelectionEvent e)
										{
											String s=(String)l.getSelectedValue();
											
													System.out.println("s14:");
											if(s!=null)
											{
													System.out.println("s12:");
												Iterator itr=ConstClient.userName.iterator();
												while(itr.hasNext()==true)
												{
													System.out.println("s1:");
													String s1=(String)itr.next();
													System.out.println("s1:"+s1);
													if(s.equals(s1))
													{
														System.out.println("list");
														int i=ConstClient.userName.indexOf(s);
														JInternalFrame j=(JInternalFrame)ConstClient.internalFrame.get(i);
														JTextArea t=(JTextArea)ConstClient.textArea.get(i);
														ta=t;
														j.toFront();
														jf.validate();
														break;
													}
												}
											}
										}
									});
		spl.setPreferredSize(new Dimension(180,350));
		p4.add(spl);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(tf);
		p2.add(send);
		label=new JLabel("My userName: "+user);
		label.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		label.setForeground(new Color(0,0,255));
		p3.add(label);
		p3.add(logout);
		jf.setLayout(new BorderLayout());
		jf.add(p3,"North");
		jf.add(p2,"South");
		jf.add(p4,"West");
		jf.setResizable(false);
		desktop=new JDesktopPane();
		jf.add(desktop,"Center");
			
		try
		{
			v=new Vector();
			ClientDb.rs=ClientDb.stmt.executeQuery("select username from chatlogin where status=1");
			while(ClientDb.rs.next()==true)
			{
				v.add(ClientDb.rs.getString("username"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("OOK");
			e.printStackTrace();
		}
				
		try
		{
			ClientDb.rs=ClientDb.stmt.executeQuery("select user,msg from time");
			while(ClientDb.rs.next()==true)
			{
			
				String sname=ClientDb.rs.getString("user");
				String line1=ClientDb.rs.getString("msg");
				if(sname.equals(user))
				{}
				else
				{
					Iterator itr=v.iterator();
					while(itr.hasNext()==true)
					{
						String sn=(String)itr.next();
						if(sname.equals(sn))
						{c=1;
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
							
							if(line1.equals("online"))
								{
									System.out.println("Online Updation");
									
									JLabel ll2=new JLabel("Online");
									System.out.println("newly created jif label:"+ll2.getText());
									r1.add(ll2);
								}
							else 
								{
									System.out.println("Last Seen Updation");
									System.out.println("time:"+line1);
									JLabel ll2=new JLabel("Last Seen:"+line1);
									System.out.println("newly created jif label:"+ll2.getText());
									r1.add(ll2);
								}
									
							jif.add(r1,BorderLayout.NORTH);
														
							jif.add(r,BorderLayout.CENTER);
							desktop.add(jif);
							
							ConstClient.userName.add(sname);
							ConstClient.internalFrame.add(jif);
							ConstClient.textArea.add(tt);
							ConstClient.panel.add(r1);	
						}
					}					
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("ew");
			e.printStackTrace();
		}
		
		if(c==1)//bcoz if--when this client window open and it was not the first client to get online then it will have some values added in ConstClient.textArea due to above working. and then below we call listUpdate so when listListeneris called we will get some value in ta.But when it is the first client it will get values in ConstClient when it will receive some msg(whether it be online or last seen msg)at that time listUpdate thread was already working so listListener was called prior to addition of values in ConstClient.textArea so we get null in textArea so msg was going to receiver but not shown on the textarea until some more client login and listListener was called again
		{
			EnabledListSelectionModel elsm=new EnabledListSelectionModel();
			l.setSelectionModel(elsm);
		}
		jf.setVisible(true);						
		jf.addWindowListener(this);	
		ListUpdate lu=new ListUpdate(l,dlm,user);
		lu.start();
		try
		{
			ClientChat cc=new ClientChat(jf,l,desktop);
			out=cc.makeStream();
			send.addActionListener(this);
		}
		catch(IOException e)
		{
			
			System.out.println("B");
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		line=tf.getText();
		if((line==null) | (line.equals("")))
		{
		}
		else
		{
			System.out.println("yes");
			ta.append("\nme:"+line);
			tf.setText("");
			line="Q!2%:"+l.getSelectedValue()+"--YU86%"+line;
			out.println(line);
			out.flush();
		}
	}
	public void windowActivated(WindowEvent e)
	{
		System.out.println("Window activated");
		line="kkll$j5jhh8onlinellk2kgg2ddd";
		out.println(line);
		out.flush();
		System.out.println("onlineflushed");
	}
	public void windowDeactivated(WindowEvent e)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("window DeActivated");
		line="kkll$j5jhh8focusllk2kgg2ddd";
		out.println(line);
		out.flush();
		System.out.println("offlineflushed");
	}
	public void windowOpened(WindowEvent e)
	{
	}
	public void windowClosed(WindowEvent e)
	{
	}
	public void windowIconified(WindowEvent e)
	{
	}
	public void windowDeiconified(WindowEvent e)
	{
	}
	public void windowClosing(WindowEvent e)
	{
	}
}
class DisabledListSelectionModel extends DefaultListSelectionModel
{
	@Override
	public void setSelectionInterval(int index0,int index1)
	{
		super.setSelectionInterval(-1,-1);
	}
}

class EnabledListSelectionModel extends DefaultListSelectionModel
{
	@Override
	public void setSelectionInterval(int index0,int index1)
	{
		super.setSelectionInterval(index0,index1);
	}
}	