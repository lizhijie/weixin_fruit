package cn.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.data.database.DataBase;
import cn.data.table.Buybus;
import cn.data.table.Goods;
import cn.data.table.Orders;

public class ShowClerk extends Clerk {
	ResultSet ret = null;
	SafeClerk safeClerk;

	public ShowClerk(SafeClerk safeClerk) {
		if (safeClerk == null)
			safeClerk = new SafeClerk(null);
		this.safeClerk = safeClerk;
	}

	public ShowClerk() {
		this.safeClerk = new SafeClerk(null);
	}

	public ArrayList<Goods> getGoods() {
		ArrayList<Goods> aGoods = new ArrayList<Goods>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Goods col = new Goods();
		// col.setName("1");
		// col.setId(1);
		// col.setAbout("1");
		// col.setImgurl("1");
		// col.setPrice(1);
		// col.setTime("1");
		// long startTime = System.currentTimeMillis();
		ret = DataBase.find(new Goods(), safeClerk.dbGateOpen(), col);
		// long endTime =
		// System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		try {
			ob = DataBase.retTo(new Goods(), ret);
			for (int i = 0; i < ob.size(); i++)
				aGoods.add((Goods) ob.get(i));
			// JSONArray json=new JSONArray(AGoods.toArrayList(aGoods));
			return aGoods;

		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public Goods getAGoods(String alias) {
		ArrayList<Goods> aGoods = new ArrayList<Goods>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Goods col = new Goods();
		Goods condi = new Goods();
		condi.setAlias(alias);
		col.setStatus(1);
		col.setPrice(1);
		col.setName("1");
		col.setGroups("1");
		col.setDesxml("1");
		col.setAlias("1");
		col.setAbout("1");
		ret = DataBase.find(condi, safeClerk.dbGateOpen(), col);
		// long endTime =
		// System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		try {
			ob = DataBase.retTo(new Goods(), ret);
			for (int i = 0; i < ob.size(); i++)
				aGoods.add((Goods) ob.get(i));
			// JSONArray json=new JSONArray(AGoods.toArrayList(aGoods));
			return aGoods.get(0);

		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Buybus> getUserBuybus() {
		ArrayList<Buybus> buybus = new ArrayList<Buybus>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Buybus col = new Buybus();
		Buybus where = new Buybus();
		where.setWho(safeClerk.userId);
		col.setAlias("1");
		col.setAbout("1");
		col.setCount(1);
		col.setName("1");
		col.setPrice(1);

		ret = DataBase.find(where, safeClerk.dbGateOpen(), col);
		try {
			ob = DataBase.retTo(new Buybus(), ret);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			buybus.add((Buybus) ob.get(i));
		return buybus;
	}

	public ArrayList<Orders> getUserOrders() {
		ArrayList<Orders> order = new ArrayList<Orders>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Orders col = new Orders();
		col.setName("1");
		// col.setAbout("1");
		col.setAlias("1");
		col.setCount(1);
		col.setNum(1);
		Orders where = new Orders();
		where.setWho(safeClerk.userId);
		ret = DataBase.find(where, safeClerk.dbGateOpen(), col);
		try {
			ob = DataBase.retTo(new Orders(), ret);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			order.add((Orders) ob.get(i));
		return order;
	}
}
