package cn.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.data.database.DataBase;
import cn.data.table.Buybus;
import cn.data.table.Cool;
import cn.data.table.Goods;
import cn.data.table.Orders;
import cn.data.table.Receiver;

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
		ret = safeClerk.getDataBase().find(new Goods(), col);
		// long endTime =
		// System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		try {
			ob = safeClerk.getDataBase().retTo(new Goods(), ret);
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
		ret = safeClerk.getDataBase().find(condi, col);
		// long endTime =
		// System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		try {
			ob = safeClerk.getDataBase().retTo(new Goods(), ret);
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

		ret = safeClerk.getDataBase().find(where, col);
		try {
			ob = safeClerk.getDataBase().retTo(new Buybus(), ret);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			buybus.add((Buybus) ob.get(i));
		return buybus;
	}

	public ArrayList<Object> getUserOrders(int num) {
		ArrayList<Orders> order = new ArrayList<Orders>();
		ArrayList<Object> ob = new ArrayList<Object>();
		Orders col = new Orders();
		col.setName("1");
		// col.setAbout("1");
		col.setAlias("1");
		col.setCount(1);
		col.setNum(1);
		col.setPrice(1);
		Orders where = new Orders();
		where.setWho(safeClerk.userId);
		if(num!=0)
		{
			where.setNum(num);
		}
		ret = safeClerk.getDataBase().find(where, col);
		try {
			ob = safeClerk.getDataBase().retTo(new Orders(), ret);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < ob.size(); i++)
			order.add((Orders) ob.get(i));
		
		ArrayList<Cool> cool = new ArrayList<Cool>();
		ArrayList<Object> obj = new ArrayList<Object>();
		Cool ccol = new Cool();
		ccol.setNum(1);
		ccol.setTime("1");
		ccol.setStatus(1);
		ccol.setRecnum("1");;
		ccol.setRecname("1");;
		ccol.setRecaddress("1");;
		Cool cwhere = new Cool();
		cwhere.setNum(num);
		cwhere.setWho(safeClerk.userId);
		ret = safeClerk.getDataBase().find(cwhere, ccol);
		try {
			obj = safeClerk.getDataBase().retTo(new Cool(), ret);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < obj.size(); i++)
			cool.add((Cool) obj.get(i));
		
		ArrayList<Object> a=new ArrayList<Object>();
		a.add(order);
		a.add(cool);
		return a;
	}

	public ArrayList<Receiver> getUserAddress(){
		return null;
		
		
	}

}
