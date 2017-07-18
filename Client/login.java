import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("deprecation")
class Login implements ActionListener
{
	private JFrame flogin;
	private ButtonGroup bg;
	private JPanel framepanel,p1,p2,p3,p4,p5;
	private JLabel lusername,lpassword,mychat,login;
	private JTextField tusername;
	private JPasswordField tpassword;
	private JButton bsubmit,bcancel,signup;
	public static int storelogin;
	Login()
	{
		flogin=new JFrame("MyChat");
		flogin.setSize(320,420);
		flogin.setLocationRelativeTo(null);
		flogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		flogin.setResizable(false);
		framepanel=(JPanel)flogin.getContentPane();
		framepanel.setLayout(new GridLayout(3,1));
		
		p1=new JPanel();
		Class c=this.getClass();
		java.net.URL url=c.getResource("/img/topic.png");
		ImageIcon ii=new ImageIcon(url);
		//ImageIcon ii=new ImageIcon("D:/my/java/advjava(jre8)/socket/Chat_client_to_client/img/topic.png");
		mychat=new JLabel(ii);
		p1.add(mychat);
		
		p2=new JPanel();
		p2.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		Insets in=new Insets(10,0,5,0);
		
		login=new JLabel("User Login");
		login.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		lusername=new JLabel("Username : ");
		lpassword=new JLabel("Password : ");
		tusername=new JTextField(15);
		tpassword=new JPasswordField(15);
		tpassword.addKeyListener(new KeyListener()
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
										ActionEvent ae=new ActionEvent(bsubmit,ActionEvent.ACTION_PERFORMED,"Submit");
										actionPerformed(ae);
									}
								}
							});
		signup=new JButton("New user !! SignUp here");
		
		gbc.insets=in;
		gbc.gridx=0;
		gbc.gridy=0;
		p2.add(login,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		p2.add(lusername,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		p2.add(tusername,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		p2.add(lpassword,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		p2.add(tpassword,gbc);
		
		p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
		bsubmit=new JButton("Submit");
		bcancel=new JButton("Cancel");
		p3.add(bsubmit);
		p3.add(bcancel);
		
		p4=new JPanel();
		p4.add(signup);
		
		p5=new JPanel();
		p5.add(p3);
		p5.add(p4);
		bsubmit.addActionListener(this);
		bcancel.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											flogin.setVisible(false);
											flogin.dispose();
											System.exit(1);
										}
									});
		
		signup.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										flogin.setVisible(false);
										flogin.dispose();
										new SignUp();
									}
								});
		framepanel.add(p1);
		framepanel.add(p2);
		framepanel.add(p5);
		
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			SwingUtilities.updateComponentTreeUI(framepanel);
		}
		catch(Exception e)
		{
			System.out.println("O");
			e.printStackTrace();
		}
		flogin.setVisible(true);
	}
public void actionPerformed(ActionEvent e)
{
	try
	{	
		ClientDb.rs=ClientDb.stmt.executeQuery("select st from status");
		ClientDb.rs.next();
		String st=ClientDb.rs.getString("st");
		if(st.equals("false"))
		{
			JOptionPane.showMessageDialog(flogin,"Server shut down!! Try again later.");
			flogin.setVisible(false);
			flogin.dispose();
			System.exit(1);
		}
		else
		{
			String user,pass;
			int checkuser=0;
			user=tusername.getText();
			pass=tpassword.getText();
			if(((user==null | user.equals(""))|(pass==null | pass.equals(""))))
			{
					JOptionPane.showMessageDialog(flogin,"Fields are empty");
					return;
			}
			ClientDb.rs=ClientDb.stmt.executeQuery("select id,username,password,status from chatlogin");
			while(ClientDb.rs.next()==true)
			{
				if(user.equals(ClientDb.rs.getString("username")))
				{
					checkuser=1;
					if(pass.equals(ClientDb.rs.getString("password")))
					{
						int stat=ClientDb.rs.getInt("status");
						if(stat==0)
						{
							storelogin=ClientDb.rs.getInt("id");
							//ClientDb.rs.close();
							ClientDb.stmt.executeUpdate("update chatlogin set status=1 where id="+storelogin);
							flogin.setVisible(false);
							flogin.dispose();
							new ClientGui();
							break;   //otherwise loop continues n causes exception:Operation not allowed after resultset closed... n we know resultset will be closed when stmt is used some other place 
						}
						else
						{
							JOptionPane.showMessageDialog(flogin,"This account is already opened ","Message",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(flogin,"Invalid Password","Message",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			if(checkuser==0 )
			{
				JOptionPane.showMessageDialog(flogin,"Invalid Username","Message",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	catch(SQLException ex)
	{
		System.out.println("N");
		ex.printStackTrace();
	}
}
	public static void main(String args[])
	{
		new Login();
	}
}