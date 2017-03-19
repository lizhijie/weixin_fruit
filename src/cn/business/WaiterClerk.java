package cn.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.data.database.DataBase;
import cn.data.table.Buybus;
import cn.data.table.Cool;
import cn.data.table.Goods;
import cn.data.table.Orders;
import cn.data.table.Receiver;

public class WaiterClerk extends Clerk {
	SafeClerk safeClerk;
	ResultSet ret = null;

	public WaiterClerk(SafeClerk safeClerk) {
		if (safeClerk == null)
			safeClerk = new SafeClerk(null);
		this.safeClerk = safeClerk;
	}

	public int delBuybus(String alias) {
		Buybus condition = new Buybus();
		condition.setWho(safeClerk.userId);
		condition.setAlias(alias);
		int i = safeClerk.getDataBase().delete(condition);
		System.out.println(i);
		return i;

	}

	public int addBuybus(String alias) {
		Buybus add = new Buybus();
		ArrayList<Buybus> repeat = new ArrayList<Buybus>();
		ArrayList<Object> reob = new ArrayList<Object>();
		Buybus recondition = new Buybus();
		recondition.setAlias(alias);
		recondition.setWho(safeClerk.userId);
		try {
			reob = safeClerk.getDataBase().retTo(new Buybus(),
					safeClerk.getDataBase().find(recondition, new Buybus()));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(reob.size()>0)
			return 0;
		
		ArrayList<Goods> aGoods = new ArrayList<Goods>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Goods condition = new Goods();
		Goods col = new Goods();
		condition.setAlias(alias);
		col.setAbout("1");
		col.setName("1");
		col.setPrice(1);
		try {
			ob = safeClerk.getDataBase().retTo(new Goods(),
					safeClerk.getDataBase().find(condition, col));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			aGoods.add((Goods) ob.get(i));
		add.setAlias(alias);
		add.setAbout(aGoods.get(0).getAbout());
		add.setName(aGoods.get(0).getName());
		add.setPrice(aGoods.get(0).getPrice());
		add.setCount(1);
		add.setTime(scanTime());
		add.setWho(safeClerk.userId);
		int i = safeClerk.getDataBase().insert(add);
		System.out.println(i);

		return i;

	}

	public boolean countBuybus(String alias, String plus) {
		ArrayList<Buybus> buybus = new ArrayList<Buybus>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Buybus col = new Buybus();
		Buybus where = new Buybus();
		where.setWho(safeClerk.userId);
		where.setAlias(alias);
		col.setCount(1);
		try {
			ob = safeClerk.getDataBase().retTo(new Buybus(),
					safeClerk.getDataBase().find(where, col));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			buybus.add((Buybus) ob.get(i));
		if (buybus.get(0) == null)
			return false;
		Buybus sqlSet = new Buybus();
		Buybus condition = new Buybus();
		condition.setAlias(alias);
		condition.setWho(safeClerk.userId);
		int p;
		if (plus.contentEquals("yes"))
			p = 1;
		else if (plus.contentEquals("no"))
			p = -1;
		else {
			return false;
		}
		System.out.println("jiajian->>"+buybus.get(0).getCount());
		sqlSet.setCount(buybus.get(0).getCount() + p);
		if (sqlSet.getCount() < 1)
			return false;
		int i = safeClerk.getDataBase().update(condition, sqlSet);
		System.out.println(i);
		if (i > 0)
			return true;
		else
			return false;

	}

	
	public int toOrders() {
		ShowClerk showclerk=new ShowClerk(this.safeClerk);
		ArrayList<Receiver> recs=null;
		recs=showclerk.getUserAddress();
		String recaddress="";
		String recnum="";
		String recname="";
		if(recs!=null)
			if(recs.size()>0)
			{
				recaddress=recs.get(0).getRecaddress();
				recname=recs.get(0).getRecname();
				recnum=recs.get(0).getRecnum();
			}
		String sql = null;
		Cool cool = new Cool();
		Cool coolWhere = new Cool();
		Cool coolCol = new Cool();
		cool.setStatus(1);
		cool.setTime(scanTime());
		cool.setWho(safeClerk.userId);
		cool.setRecaddress(recaddress);
		cool.setRecnum(recnum);
		cool.setRecname(recname);
		safeClerk.getDataBase().insert(cool);
		coolWhere.setWho(safeClerk.userId);
		coolCol.setNum(1);
		ret = safeClerk.getDataBase().find(coolWhere, coolCol);
		int number = 0;
		try {
			ret.afterLast();
			ret.last();
			number = ret.getInt(1);
			System.out.println(number);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		safeClerk.getDataBase().status = false;
		Orders value = new Orders();
		safeClerk.getDataBase().insert(value);
		sql = safeClerk.getDataBase().sq;
		sql = sql.split("values")[0];
		sql = sql.replace("id,", "");
		sql = sql.replace(",id", "");
		Buybus condition = new Buybus();
		condition.setWho(safeClerk.userId);
		safeClerk.getDataBase().find(condition, new Buybus());
		String sql2 = safeClerk.getDataBase().sq;
		String sql3=sql.split("\\(")[1];
		sql3=" "+sql3.split("\\)")[0]+" ";
		sql3=sql3.replace("num", number+"");
		System.out.println("===="+sql3+"===");
		sql2 = sql2.replace("*",sql3);
		sql2 = sql + sql2;
		System.out.println(sql2);
		int i = safeClerk.getDataBase().run( sql2);
		if(i<=0)
			return 0;
		safeClerk.getDataBase().status = true;
		if(i>0)
		{
			Buybus clearBuybus=new Buybus();
			clearBuybus.setWho(safeClerk.userId);
			safeClerk.getDataBase().delete(clearBuybus);
		}
		return number;
	}

	public int delOrders(int num) {
		Orders condition = new Orders();
		condition.setWho(safeClerk.userId);
		condition.setNum(num);
		Cool con = new Cool();
		con.setWho(safeClerk.userId);
		con.setNum(num);
		int s=safeClerk.getDataBase().delete(con);
		int i = safeClerk.getDataBase().delete(condition);
		System.out.println(i);
		return i;

	}
	public int updateCoolAddress(ArrayList<String> rec,int num)
	{
		Cool address=new Cool();
		Cool where=new Cool();
		where.setWho(safeClerk.userId);
		where.setNum(num);
		address.setRecname(rec.get(0));
		address.setRecnum(rec.get(1));
		address.setRecaddress(rec.get(2));
		int i=safeClerk.getDataBase().update(where, address);
		if(i>0)
			return i;
		return 0;
	}
}
