import javax.swing.*;
import java.sql.*;
import java.util.*;
public class ListUpdate extends Thread
{
	private DefaultListModel dlm;
	private Vector s;
	private int count,c;
	private String user;
	private JList l;
	ListUpdate(JList l,DefaultListModel dlm,String user)
	{
		this.l=l;
		this.dlm=dlm;
		s=new Vector();
		this.user=user;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(1500);
				//System.out.println("lo");
				ClientDb.rs=ClientDb.stmt.executeQuery("select * from chatlogin where status=1");
				while(ClientDb.rs.next()==true)
				{
					count=0;
					String uname=ClientDb.rs.getString("username");
					Iterator itr=s.iterator();
					while(itr.hasNext()==true)
					{
						String s1=(String)itr.next();
						if(uname.equals(s1))
						{
							count=1;
							break;
						}
					}
					if(count==0)
					{
						if(uname.equals(user))
						{}
						else
						{
							dlm.addElement(uname);
							s.add(uname);
							if(c==0)
							{
								l.setSelectedIndex(0);
							}
						}
					}
				}
				ClientDb.rs=ClientDb.stmt.executeQuery("select * from chatlogin where status=0");
				while(ClientDb.rs.next()==true)
				{
					Iterator itr=s.iterator();
					String uname=ClientDb.rs.getString("username");
					while(itr.hasNext()==true)
					{
						String s1=(String)itr.next();
						if(uname.equals(s1))
						{
							l.setSelectedIndex(-1);
							dlm.removeElement(uname);
							int index=s.indexOf(uname);
							s.remove(index);
							int in=ConstClient.userName.indexOf(uname);
							ConstClient.userName.remove(in);
							JInternalFrame jj=(JInternalFrame)ConstClient.internalFrame.get(in);
							jj.setClosed(true);
							ConstClient.internalFrame.remove(in);
							ConstClient.textArea.remove(in);
						}
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("po");
				e.printStackTrace();
			}
		}	
	}
}