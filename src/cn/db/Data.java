package cn.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





public class Data {
	public String sql = null;
	public DB db1 = null;
	public ResultSet ret = null;
	public ArrayList<AGoods> getGoods()
	{
	ArrayList<AGoods> agoods=new ArrayList<AGoods>();
	db1 = new DB();
	db1.connect();
	ret=AGoods.find(db1.conn);
	ChangeADB change = new ChangeADB(ret);
			try {
				change.retTo(agoods);
				//JSONArray json=new JSONArray(AGoods.toArrayList(agoods));
				return agoods;
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;

	}

}
