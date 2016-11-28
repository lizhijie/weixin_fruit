package cn.data.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	public boolean status=true;
	public String sq="";
	public Connection conn;
	public int run(String sql){
		int i=0;
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int insert(Object object) {
		// 定义一个sql字符串
		String sql = "insert into ";
		// 得到对象的类
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		List<String> mList=refl.getmList();
		List vList=refl.getvList();
		ArrayList<Method> setMethods=refl.getSetMethods();
		Class c=refl.getC();
		sql += tableName + "(";
		
		for (int i = 0; i < mList.size(); i++) {
			if (i < mList.size() - 1) {
				sql += mList.get(i) + ",";
			} else {
				sql += mList.get(i) + ") values(";
			}
		}
		for (int i = 0; i < vList.size(); i++) {
			if (i < vList.size() - 1) {
				sql += vList.get(i) + ",";
			} else {
				sql += vList.get(i) + ")";
			}
		}
		System.out.println(sql);
		int i = 0;
		sq=sql;
		if(status){
			i=run(sql);
		}
		return i;

	}

	public ResultSet find(Object condition,Object col) {
		String sql = "select"+column(col)+" from " + getTableName(condition)
				+ where(condition);

		System.out.print(sql);
		sq=sql;
		if(status){
		try {

			PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
			return pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return null;
	}

	public int delete(Object condition) {
		int i = 0;
		// delete from goods
		String sql = "delete from " + getTableName(condition)
				+ where(condition);

		System.out.println(sql);
		sq=sql;
		if(status){
			i=run(sql);
		}
		return i;
	}

	public int update(Object condition, Object sqlSet) {
		String sql = "update " + getTableName(sqlSet) + set(sqlSet)
				+ where(condition);
		System.out.println(sql);
		int i = 0;
		sq=sql;
		if(status){
			i=run(sql);
		}
		return i;
	}

	public String where(Object object) {
		String sql = " where";
		// 得到对象的类
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		List<String> mList=refl.getmList();
		List vList=refl.getvList();
		ArrayList<Method> setMethods=refl.getSetMethods();
		Class c=refl.getC();
		boolean flag = false;
		for (int i = 0; i < mList.size(); i++) {
			if (vList.get(i) != null && vList.get(i) != ""
					&& !((vList.get(i) + "").contentEquals("0"))) {
				if (flag)
					sql += " and";
				flag = true;
				sql += " " + mList.get(i) + "=" + vList.get(i);
			}
		}
		if (sql.replace(" ", "").contentEquals("where")) {
			sql = "";
		}
		System.out.println(sql);
		return sql;

	}

	public String set(Object object) {
		String sql = " set";
		// 得到对象的类
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		List<String> mList=refl.getmList();
		List vList=refl.getvList();
		ArrayList<Method> setMethods=refl.getSetMethods();
		Class c=refl.getC();
		boolean flag = false;
		for (int i = 0; i < mList.size(); i++) {
			if (vList.get(i) != null && vList.get(i) != ""
					&& !((vList.get(i) + "").contentEquals("0"))) {
				if (flag)
					sql += " ,";
				flag = true;
				sql += " " + mList.get(i) + "=" + vList.get(i);
			}
		}
		if (sql.replace(" ", "").contentEquals("set")) {
			sql = "";
		}
		System.out.println(sql);
		return sql;

	}

	public String column(Object object) {
		String sql = "";
		// 得到对象的类
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		List<String> mList=refl.getmList();
		List vList=refl.getvList();
		ArrayList<Method> setMethods=refl.getSetMethods();
		Class c=refl.getC();
		boolean flag = false;
		for (int i = 0; i < mList.size(); i++) {
			if (vList.get(i) != null && vList.get(i) != ""
					&& !((vList.get(i) + "").contentEquals("0"))) {
				if (flag)
					sql += " ,";
				flag = true;
				sql += " " + mList.get(i);
			}
		}
		if (sql.replace(" ", "").contentEquals("")) {
			sql = " *";
		}
		//System.out.println(sql+"--->>col");
		return sql;

	}
	
	public String getTableName(Object object) {
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		return tableName;
	}

	public ArrayList<Object> retTo(Object object, ResultSet ret)
			throws NumberFormatException, SQLException {
		ArrayList<Object> agoods = new ArrayList<Object>();
		// System.out.println(ret.);
		DataReflect refl=new DataReflect(object);
		String tableName=refl.getTableName();
		List<String> mList=refl.getmList();
		List vList=refl.getvList();
		ArrayList<Method> setMethods=refl.getSetMethods();
		Class c=refl.getC();
		while (ret.next()) {
			Object myObject = null;
			try {
				myObject = c.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Type[] t1;
			for (int i = 0; i < setMethods.size(); i++) {
				String mName = setMethods.get(i).getName();
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				try {
					t1 = setMethods.get(i).getParameterTypes();
					//System.out.println(t1[0]);
					try{
						if(ret.findColumn(fieldName)>0)
					if (t1[0].toString().contentEquals("int"))
						setMethods.get(i).invoke(
								myObject,
								Integer.parseInt(ret.getString(fieldName)));
					else
						setMethods.get(i).invoke(myObject,
								ret.getString(fieldName));
					}
						 catch (SQLException e) {
						    
						    }
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			agoods.add(myObject);
		}
		return agoods;
	}
}
