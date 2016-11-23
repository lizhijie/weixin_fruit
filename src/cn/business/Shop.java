package cn.business;

import java.util.ArrayList;

import cn.data.table.*;

public class Shop {
	protected SafeClerk safeClerk;
	protected WaiterClerk waiter;
	protected ShowClerk showClerk;

	public Shop(String userId) {
		safeClerk = new SafeClerk(userId);
		showClerk = new ShowClerk(safeClerk);
		waiter = new WaiterClerk(safeClerk);
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

	public ArrayList<Orders> orders() {
		if (safeClerk.isSafeUser())
			return showClerk.getUserOrders();
		else
			return new ArrayList<Orders>();
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

	public boolean toOrders() {
		if (safeClerk.isSafeUser()) {
			int num = waiter.toOrders();
			if (num > 0)
				return true;
			else
				return false;
		} else
			return false;

	}
}
