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
		int i = DataBase.delete(condition, safeClerk.dbGateOpen());
		System.out.println(i);
		return i;

	}

	public int addBuybus(String alias) {
		Buybus add = new Buybus();
		ArrayList<Goods> aGoods = new ArrayList<Goods>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Goods condition = new Goods();
		Goods col = new Goods();
		condition.setAlias(alias);
		col.setAbout("1");
		col.setName("1");
		col.setPrice(1);
		try {
			ob = DataBase.retTo(new Goods(),
					DataBase.find(condition, safeClerk.dbGateOpen(), col));
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
		add.setTime("2016-11-19 15:49:40");
		add.setWho(safeClerk.userId);
		int i = DataBase.insert(add, safeClerk.dbGateOpen());
		System.out.println(i);

		return i;

	}

	public boolean countBuybus(String alias, String plus) {
		ArrayList<Buybus> buybus = new ArrayList<Buybus>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Buybus col = new Buybus();
		Buybus where = new Buybus();
		where.setWho(safeClerk.userId);
		col.setCount(1);
		try {
			ob = DataBase.retTo(new Buybus(),
					DataBase.find(where, safeClerk.dbGateOpen(), col));
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
		sqlSet.setCount(buybus.get(0).getCount() + p);
		if (sqlSet.getCount() < 1)
			sqlSet.setCount(1);
		int i = DataBase.update(condition, safeClerk.dbGateOpen(), sqlSet);
		System.out.println(i);
		if (i > 0)
			return true;
		else
			return false;

	}

	public int toOrders() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String sql = null;
		Cool cool = new Cool();
		Cool coolWhere = new Cool();
		Cool coolCol = new Cool();
		cool.setStatus(1);
		cool.setTime(df.format(new Date()));
		cool.setWho(safeClerk.userId);
		DataBase.insert(cool, safeClerk.dbGateOpen());
		coolWhere.setWho(safeClerk.userId);
		coolCol.setNum(1);
		ret = DataBase.find(coolWhere, safeClerk.dbGateOpen(), coolCol);
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

		DataBase.status = false;
		Orders value = new Orders();
		DataBase.insert(value, safeClerk.dbGateOpen());
		sql = DataBase.sq;
		sql = sql.split("values")[0];
		sql = sql.replace("id,", "");
		sql = sql.replace("about,", "");
		Buybus condition = new Buybus();
		Buybus col = new Buybus();
		condition.setWho(safeClerk.userId);
		col.setName("1");
		col.setCount(1);
		col.setPrice(1);
		col.setWho("1");
		col.setAlias("1");
		col.setTime("");
		DataBase.find(condition, safeClerk.dbGateOpen(), col);
		String sql2 = DataBase.sq;
		sql2 = sql2.replace("time", "'" + df.format(new Date()) + "'");
		sql2 = sql2.replace("select", "select " + number + ",");
		sql2 = sql + sql2;
		System.out.println(sql2);
		int i = DataBase.run(safeClerk.dbGateOpen(), sql2);
		DataBase.status = true;
		return i;
	}

	public int delOrders(int num) {
		Orders condition = new Orders();
		condition.setWho(safeClerk.userId);
		condition.setNum(num);
		int i = DataBase.delete(condition, safeClerk.dbGateOpen());
		System.out.println(i);
		return i;

	}
}
