package cn.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {
	public String sql = null;
	public DB db1 = null;
	public ResultSet ret = null;
	/*public void add()
	{
	AGoods agoods=new AGoods();
	AGoods xigua=new AGoods();
	xigua.price=78;
	xigua.about="���Ͻ����ʣ��ֱ���";
	xigua.imgurl="/����";
	agoods.name="����";
	db1 = new DB();
	db1.connect();
	AGoods.delete(agoods,db1.conn);
	}*/

	public void getAllGoods(ArrayList<AGoods> agoods) {
		{
			sql = "select *from goods";// SQL���
			db1 = new DB();// ����DBHelper����
			try {
				db1.connect();
				ret = db1.query(sql);// ִ����䣬�õ������
				
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
				db1.close();// �ر�����

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
