package cn.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.data.table.Goods;
import cn.debug.MyDebug;

public class AdminClerk extends Clerk {
SafeClerk safeClerk;
	public AdminClerk(SafeClerk safeClerk) {
		// TODO Auto-generated constructor stub
		super();
		this.safeClerk=safeClerk;
	}

	public int addGoods(Goods goods) {
		// TODO Auto-generated method stub
		int i=0,j=0;
		Goods condition=new Goods();
		condition.setAlias(goods.getAlias());
		i=safeClerk.getDataBase().getRowCount(safeClerk.getDataBase().find(condition, new Goods()));
		if(i<=0)
		j=safeClerk.getDataBase().insert(goods); 
		MyDebug.println(this, j+"");
		return j;
	}

	public int delGoods(String alias) {
		// TODO Auto-generated method stub
		Goods condition=new Goods();
		condition.setAlias(alias);
		return safeClerk.getDataBase().delete(condition);
	}

	public int updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		Goods where=new Goods();
		where.setAlias(goods.getAlias());
		int i=safeClerk.getDataBase().update(where,goods);
		return i;
	}
}
