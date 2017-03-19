package cn.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.data.database.DB;
import cn.data.database.DataBase;
import cn.data.table.Goods;
import cn.data.table.User;

public class SafeClerk extends Clerk {
	private DB db1 = null;
	private Connection con = null;
	protected String userId;
	private DataBase database=null;

	public SafeClerk(String userId) {
		this.userId = userId;
		if(!exist()&&isSafeUser())
			register();
		
	}

	
	protected DataBase getDataBase()
	{
		if(database==null)
		{
			database=new DataBase();
		}
		database.conn=dbGateOpen();
		return database;
	}
	
	
	protected Connection dbGateOpen() {
		if (db1 == null)
			db1 = new DB();
		if (con == null) {
			db1.connect();
			con = db1.conn;
		}
		try {
			if (con.isClosed())
				db1.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}

	protected void dbGateClose() {
		if (db1 != null) {
			if (con != null) {

				try {
					if (!con.isClosed())
						db1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected boolean isSafeUser() {
		// if(userId==null)
		// return false;
		return true;
	}

	protected void finalize() {

		this.dbGateClose();

	}

	public boolean checkString(String string) {
		if (string == null)
			return false;
		if (string == "")
			return false;
		return true;
	}

	public boolean checkInt(int num) {
		if (num < 1)
			return false;
		if (num > 200000000)
			return false;
		return true;
	}
	
	public boolean exist()
	{
		ArrayList<Object> ob = new ArrayList<Object>();
		User user=new User();
		user.setWeixin(userId);
		try {
			ob=this.getDataBase().retTo(new User(),this.getDataBase().find(user, new User()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ob.size()>0)
		{
			
			System.out.println(userId+"------>exist");
			return true;
		}
		else
		{
			System.out.println(userId+"------>not exist");
			return false;
		}
	}
	public boolean register()
	{
		User user=new User();
		user.setWeixin(userId);
		user.setUsername("");
		user.setStatus(1);
		user.setTime(scanTime());
		int i=0;
		i=this.getDataBase().insert(user);
		if(i>0)
		return true;
		return false;
	}
}
