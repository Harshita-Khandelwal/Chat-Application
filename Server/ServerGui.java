import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
class ServerGui extends JFrame
{
	public JTextArea ta;
	private JScrollPane sp,spl;
	private	JPanel p3,p4;
	private DefaultListModel dlm;
	private JList l;
	private String line;
	ServerGui()
	{
		setSize(500,420);
		setLocation(200,200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		p3=new JPanel();
		p4=new JPanel();
		ta=new JTextArea(21,25);
		ta.setEditable(false);
		sp=new JScrollPane(ta);
		dlm=new DefaultListModel();
		l=new JList(dlm);
		spl=new JScrollPane(l);
		spl.setPreferredSize(new Dimension(180,340));
		p3.add(sp);
		p4.add(spl);
	setLayout(new BorderLayout());
		add(p4,"East");
		add(p3);
		setResizable(false);
		setVisible(true);
		//Constant.o=new Object[2][];
		//Constant.o[0]=new Object[10];
		//Constant.o[1]=new Object[10];
		Constant.index=new Vector();
		Constant.socketObj=new Vector();
		try
		{
			ServerChat sc=new ServerChat(ta,dlm,l);
			sc.start();
		}
		catch(IOException e)
		{
			System.out.println("J");
		}
		try
		{
			Db.stmt.execute("update status set st='true'");
		}
		catch(SQLException e)
		{
			System.out.println("z");
		}
}
	public static void main(String args[])
	{
		new ServerGui();
	}
}