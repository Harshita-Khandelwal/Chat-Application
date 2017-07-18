import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class SignUp
{
	private JFrame fsignup;
	private JPanel framepanel,p1,p11,p12,p2;
	private JLabel signup,acc,username,password,cpassword,name,mobile;
	private JTextField tusername,tname,tmobile;
	private JPasswordField tpassword,tcpassword;
	private JButton submit,cancel;
	SignUp()
	{
		fsignup=new JFrame("MyChat");
		fsignup.setSize(320,420);
		fsignup.setLocationRelativeTo(null);
		fsignup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		fsignup.setResizable(false);
		framepanel=(JPanel)fsignup.getContentPane();
		framepanel.setLayout(new BorderLayout());

		signup=new JLabel("SignUp");
		signup.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		signup.setForeground(new Color(0,0,255));
		acc=new JLabel("Create New Account");
		acc.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		p1=new JPanel();
		p11=new JPanel();
		p12=new JPanel();
		p1.setLayout(new GridLayout(2,1));
		p11.setLayout(new FlowLayout(FlowLayout.LEFT));
		p12.setLayout(new FlowLayout(FlowLayout.CENTER));
		p11.add(signup);
		p12.add(acc);
		p1.add(p11);
		p1.add(p12);
		framepanel.add(p1,"North");
		
		username=new JLabel("UserName :");
		password=new JLabel("Password :");
		cpassword=new JLabel("Confirm Password :");
		name=new JLabel("Name :");
		mobile=new JLabel("Mobile No :");
		
		tusername=new JTextField(10);
		tpassword=new JPasswordField(10);
		tcpassword=new JPasswordField(10);
		tname=new JTextField(10);
		tmobile=new JTextField(10);
		tmobile.addKeyListener(new KeyListener()
									{
										public void keyTyped(KeyEvent e)
										{}
										public void keyReleased(KeyEvent e)
										{}
										public void keyPressed(KeyEvent e)
										{
											int k;
											k=e.getKeyCode();
											if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
											{ 
												if(tmobile.getText()!=null && tmobile.getText().length()>9)
												{
													tmobile.setEditable(false);
													tmobile.setBackground(Color.WHITE);
												}
												else
													tmobile.setEditable(true);
											}
											else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
											{
												tmobile.setEditable(true);
											}
											else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
											{
												tmobile.setEditable(true);
											}
											else
											{
												tmobile.setEditable(false);
												tmobile.setBackground(Color.WHITE);
											}
										}
									});
		
		submit=new JButton("Submit");
		cancel=new JButton("Cancel");
		
		p2=new JPanel();
		p2.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		Insets in=new Insets(10,0,5,0);
		gbc.insets=in;
		
		gbc.gridx=0;
		gbc.gridy=0;
		p2.add(name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		p2.add(tname,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		p2.add(mobile,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		p2.add(tmobile,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		p2.add(username,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		p2.add(tusername,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		p2.add(password,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		p2.add(tpassword,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		p2.add(cpassword,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		p2.add(tcpassword,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		p2.add(submit,gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		p2.add(cancel,gbc);
		
		framepanel.add(p2,"Center");
		
		fsignup.setVisible(true);
		
		submit.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											try
											{
											int id;
											String uname,umob,user,upass,confpass;
											uname=tname.getText();
											umob=tmobile.getText();
											user=tusername.getText();
											upass=tpassword.getText();
											confpass=tcpassword.getText();
											if((uname==null | uname.equals(""))|(umob==null | umob.equals(""))|(user==null | user.equals(""))|(upass==null | upass.equals(""))|(confpass==null | confpass.equals("")))
											{
													JOptionPane.showMessageDialog(fsignup,"Fields are empty");
													return;
											}
											if(uname.length()>20)
											{
												JOptionPane.showMessageDialog(fsignup,"Name cannot be more than 20 characters");
													return;
											}
											if(umob.length()!=10)
											{
												JOptionPane.showMessageDialog(fsignup,"Invalid mobile no");
													return;
											}
											if(user.length()>10)
											{
												JOptionPane.showMessageDialog(fsignup,"Username cannot be more than 10 characters");
													return;
											}
											if(upass.length()>10)
											{
												JOptionPane.showMessageDialog(fsignup,"Password cannot be more than 10 characters");
													return;
											}
											if(!(upass.equals(confpass)))
											{
												JOptionPane.showMessageDialog(fsignup,"Confirm Password field is wrong");
												return;
											}
												ClientDb.rs=ClientDb.stmt.executeQuery("select max(id) as mid from chatlogin");
												ClientDb.rs.next();
												id=ClientDb.rs.getInt("mid");
												//ClientDb.rs.close();
												id++;
												
											LoginBean lb=new LoginBean(id,uname,umob,user,upass,0);
											int i=lb.createUser();
											if(i==0)
											{
												JOptionPane.showMessageDialog(fsignup,"This username already exists");
												return;
											}
											else if(i==1)
											{
												JOptionPane.showMessageDialog(fsignup,"Account Successfully created");
												fsignup.setVisible(false);
												fsignup.dispose();
												Login l=new Login();
											}
											}
											catch(SQLException ex)
											{
												System.out.println("P");	
												}
										}
									});
		cancel.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											fsignup.setVisible(false);
											fsignup.dispose();
											Login l=new Login();
										}
									});
	}
}