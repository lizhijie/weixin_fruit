package cn.business;

import java.util.ArrayList;

import cn.data.table.*;

public class Shop {
	protected SafeClerk safeClerk;
	protected WaiterClerk waiter;
	protected ShowClerk showClerk;
	protected AdminClerk adminClerk;

	public Shop(String userId) {
		safeClerk = new SafeClerk(userId);
		showClerk = new ShowClerk(safeClerk);
		waiter = new WaiterClerk(safeClerk);
		adminClerk=new AdminClerk(safeClerk);
	}

	public ArrayList<Goods> index() {
		if (safeClerk.isSafeUser())
			return showClerk.getGoods();
		else
			return new ArrayList<Goods>();
	}

	public ArrayList<Buybus> buyBus() {
		if (safeClerk.isSafeUser()) {
			return showClerk.getUserBuybus();
		} else
			return new ArrayList<Buybus>();
	}

	public Goods getAGoods(String alias) {
		if (safeClerk.isSafeUser() && safeClerk.checkString(alias))
			return showClerk.getAGoods(alias);
		else
			return new Goods();
	}

	public boolean delBuybus(String alias) {
		if (safeClerk.isSafeUser()) {
			if (!safeClerk.checkString(alias))
				return false;
			int num = waiter.delBuybus(alias);
			if (num > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean addBuyBus(String alias) {
		if (safeClerk.isSafeUser()) {
			if (!safeClerk.checkString(alias))
				return false;
			int num = waiter.addBuybus(alias);
			if (num > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean countBuybus(String alias, String plus) {
		if (safeClerk.isSafeUser()) {
			if (!safeClerk.checkString(alias))
				return false;
			boolean num = waiter.countBuybus(alias, plus);
			if (num)
				return true;
			else
				return false;
		} else
			return false;
	}

	public ArrayList<Object> orders(int status) {
		if (safeClerk.isSafeUser())
			return showClerk.getUserOrders(0,status);
		else
			return new ArrayList<Object>();
	}
	public ArrayList<Object> getAOrders(int num) {
		if (safeClerk.isSafeUser())
			return showClerk.getUserOrders(num,0);
		else
			return new ArrayList<Object>();
	}

	public boolean delOrders(int orders) {
		if (safeClerk.isSafeUser()) {
			if (!safeClerk.checkInt(orders))
				return false;
			int num = waiter.delOrders(orders);
			if (num > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public int toOrders() {
		if (safeClerk.isSafeUser()) {
			int num = waiter.toOrders();
			if (num > 0)
				return num;
			else
				return 0;
		} else
			return 0;

	}
	public int updateCoolAddress(ArrayList<String> rec,int num)
	{
		if (safeClerk.isSafeUser()) {
			int i = waiter.updateCoolAddress(rec, num);
			if (i > 0)
				return i;
			else
				return 0;
		} else
			return 0;
	}

	public Receiver defalutRec() {
		// TODO Auto-generated method stub
		if (safeClerk.isSafeUser()) {
			return showClerk.getUserAddress().get(0);
		} else
		return null;
	}

	public int updateDefaultRec(String recname, String recnum,
			String recaddress) {
		// TODO Auto-generated method stub
		if (safeClerk.isSafeUser()) {
			int i = waiter.updateDefaultRec(recname,recnum,recaddress);
			if (i > 0)
				return i;
			else
				return 0;
		} else
			return 0;
}

	public SafeClerk getSafeClerk() {
		return safeClerk;
	}

	public void setSafeClerk(SafeClerk safeClerk) {
		this.safeClerk = safeClerk;
	}

	public int addGoods(Goods goods) {
		// TODO Auto-generated method stub
		return adminClerk.addGoods(goods);
	}

	public int delGoods(String alias) {
		// TODO Auto-generated method stub
		return adminClerk.delGoods(alias);
	}

	public int updateGoods(Goods goods) {
		return adminClerk.updateGoods(goods);
		// TODO Auto-generated method stub
		
	}
}
