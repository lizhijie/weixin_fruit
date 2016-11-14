package cn.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shop {
	public String sql = null;
	public DB db1 = null;
	public ResultSet ret = null;

	public ArrayList<Goods> getGoods() {
		ArrayList<Goods> agoods = new ArrayList<Goods>();
		ArrayList<Object> ob = new ArrayList<Object>();
		db1 = new DB();
		db1.connect();
		Goods col=new Goods();
		col.name="1";
		col.price=1;
		col.about="2";
		ret = Data.find(new Goods(), db1.conn,col);
		try {
			ob = Data.retTo(new Goods(), ret);
			for (int i = 0; i < ob.size(); i++)
				agoods.add((Goods) ob.get(i));
			// JSONArray json=new JSONArray(AGoods.toArrayList(agoods));
			return agoods;

		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
