package cn.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.data.database.DB;
import cn.data.database.DataBase;
import cn.data.table.Goods;
import cn.data.table.Receiver;
import cn.data.table.User;
import cn.debug.MyDebug;

public class SafeClerk extends Clerk {
	private DB db1 = null;
	private Connection con = null;
	protected String userId;
	protected String userName;
	private DataBase database=null;

	public SafeClerk(String userId) {
		this.userId = userId;
		if(!exist()&&isSafeUser())
			register();
		
	}
	
	public SafeClerk() {
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
			setUserName(((User) ob.get(0)).getUsername());
			MyDebug.println(this,userId+"------>exist");
			return true;
		}
		else
		{
			MyDebug.println(this,userId+"------>not exist");
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
		Receiver receiver=new Receiver();
		receiver.setWeixin(userId);
		receiver.setRecname("");
		receiver.setRecnum("");
		receiver.setRecaddress("");
		int k=0;
		k=this.getDataBase().insert(receiver);
		if(i>0&&k>0)
		return true;
		return false;
	}

	public static int creatWebUser(String creatUser,String loginName,String passwd)
	{
		User user=new User();
		User condition=new User();
		User sqlSet=new User();
		SafeClerk little=new SafeClerk();
		String weixin=webLoginchange(loginName,null);
		String weixincode=webLoginchange(loginName,passwd);
		
		if(creatUser==null)
			creatUser="";
		if((!(creatUser.trim().contentEquals("")))&&creatUser.trim().length()>20)
		{
			if(weixin==null)
			{
				condition.setWeixin(creatUser);
				sqlSet.setUsername(loginName);
				sqlSet.setMd5(passwd);
				little.getDataBase().update(condition, sqlSet);
				return 1;//网页用户不存在新建网页用户并绑定微信
			}
			else if(weixincode==null)
			{
				return -1;//网页账户密码不正确无法绑定
			}
			else
			{
				condition.setWeixin(creatUser);
				sqlSet.setUsername(loginName);
				sqlSet.setMd5(passwd);
				little.getDataBase().update(condition, sqlSet);
				condition=new User();
				sqlSet=new User();
				condition.setUsername(loginName);
				condition.setMd5(passwd);
				sqlSet.setWeixin(creatUser);
				little.getDataBase().update(condition, sqlSet);
				return 2;//绑定成功
			}
		}
		else
		{
			if(weixin==null)
			{
				user.setUsername(loginName);
				user.setMd5(passwd);
				user.setStatus(1);
				user.setWeixin(System.currentTimeMillis()+getRandomString(5));
				user.setTime(scanTime());
			int i=little.getDataBase().insert(user);
			if(i>0)
			return 3;//网页注册成功
			}
			else
			{
				return -2;//网页注册失败用户已存在
			}
		}
		return -3;
	}
	public static String  webLoginchange(String loginName,String passwd)
	{
		ArrayList<Object> userList=new ArrayList<Object> ();
		User condition=new User();
		User col=new User();
		SafeClerk little=new SafeClerk();
		col.setWeixin("1");
		condition.setUsername(loginName);
		condition.setMd5(passwd);
		try {
			userList=little.getDataBase().retTo(col,little.getDataBase().find(condition, col));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userList.size()>0)
		{
			String weixin=((User)(userList.get(0))).getWeixin();
			return weixin;
		}
		return null;
	}
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}
