package cn.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {
	public String sql = null;
	public DB db1 = null;
	public ResultSet ret = null;

	public void getAllGoods(ArrayList<AGoods> agoods) {
		{
			sql = "select *from goods";// SQL语句
			db1 = new DB();// 创建DBHelper对象
			try {
				db1.connect();
				ret = db1.query(sql);// 执行语句，得到结果集
				
				ChangeADB change = new ChangeADB(ret);
				change.retTo(agoods);
				int i;
				for (i = 0; i < agoods.size() && agoods.get(i) != null; i++) {
					System.out.println(agoods.get(i).id + "\t"
							+ agoods.get(i).name + "\t" + agoods.get(i).imgurl
							+ "\t" + agoods.get(i).about + "\t"
							+ agoods.get(i).price);
				}
				ret.close();
				db1.close();// 关闭连接

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
