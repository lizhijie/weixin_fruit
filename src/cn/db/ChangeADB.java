package cn.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeADB {
	private ResultSet ret = null;

	public ChangeADB(ResultSet ret) {
		super();
		this.ret = ret;
	}

	public void retTo(ArrayList<AGoods> agoods) throws NumberFormatException,
			SQLException {
		while (ret.next()) {
			AGoods myGoods = new AGoods();
			myGoods.id = Integer.parseInt(ret.getString(1));
			myGoods.name = ret.getString(2);
			myGoods.imgurl = ret.getString(3);
			myGoods.about = ret.getString(4);
			myGoods.price = Integer.parseInt(ret.getString(5));
			agoods.add(myGoods);
		}
	}
}
