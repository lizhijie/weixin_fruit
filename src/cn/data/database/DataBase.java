package cn.data.database;

import java.lang.reflect.Field;
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
	public static boolean status=true;
	public static String sq="";
	public static int run(Connection conn,String sql){
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
	public static int insert(Object object, Connection conn) {
		// 定义一个sql字符串
		String sql = "insert into ";
		// 得到对象的类
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		// 得到对象中所有的属性
		Field[] fields = c.getFields();
		// 得到对象类的名字
		String cName = c.getName();
		// 从类的名字中解析出表名
		String tableName = cName.substring(cName.lastIndexOf(".") + 1,
				cName.length()).toLowerCase();
		sql += tableName + "(";
		List<String> mList = new ArrayList<String>();
		List vList = new ArrayList();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get") && !mName.startsWith("getClass")) {
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				mList.add(fieldName);
				try {
					Object value = method.invoke(object, null);
					if (value instanceof String) {
						vList.add("\"" + value + "\"");
					} else {
						vList.add(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
		}
		return i;

	}

	public static ResultSet find(Object condition, Connection conn,Object col) {
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

	public static int delete(Object condition, Connection conn) {
		int i = 0;
		// delete from goods
		String sql = "delete from " + getTableName(condition)
				+ where(condition);

		System.out.println(sql);
		sq=sql;
		if(status){
		try {

			PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
			i = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return i;
	}

	public static int update(Object condition, Connection conn, Object sqlSet) {
		String sql = "update " + getTableName(sqlSet) + set(sqlSet)
				+ where(condition);
		System.out.println(sql);
		int i = 0;
		sq=sql;
		if(status){
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
		}
		return i;
	}

	public static String where(Object object) {
		String sql = " where";
		// 得到对象的类
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		// 得到对象中所有的属性
		Field[] fields = c.getFields();
		// 得到对象类的名字
		List<String> mList = new ArrayList<String>();
		List vList = new ArrayList();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get") && !mName.startsWith("getClass")) {
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				mList.add(fieldName);
				try {
					Object value = method.invoke(object, null);
					if (value instanceof String) {
						vList.add("\'" + value + "\'");
					} else {
						vList.add(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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

	public static String set(Object object) {
		String sql = " set";
		// 得到对象的类
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		// 得到对象中所有的属性
		Field[] fields = c.getFields();
		// 得到对象类的名字
		List<String> mList = new ArrayList<String>();
		List vList = new ArrayList();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get") && !mName.startsWith("getClass")) {
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				mList.add(fieldName);
				try {
					Object value = method.invoke(object, null);
					if (value instanceof String) {
						vList.add("\'" + value + "\'");
					} else {
						vList.add(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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

	public static String column(Object object) {
		String sql = "";
		// 得到对象的类
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		// 得到对象中所有的属性
		Field[] fields = c.getFields();
		// 得到对象类的名字
		List<String> mList = new ArrayList<String>();
		List vList = new ArrayList();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("get") && !mName.startsWith("getClass")) {
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				mList.add(fieldName);
				try {
					Object value = method.invoke(object, null);
					if (value instanceof String) {
						vList.add("\'" + value + "\'");
					} else {
						vList.add(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
	
	public static String getTableName(Object object) {
		Class c = object.getClass();
		String cName = c.getName();
		String tableName = cName.substring(cName.lastIndexOf(".") + 1,
				cName.length()).toLowerCase();
		return tableName;
	}

	public static ArrayList<Object> retTo(Object object, ResultSet ret)
			throws NumberFormatException, SQLException {
		ArrayList<Object> agoods = new ArrayList<Object>();
		// System.out.println(ret.);
		Class c = object.getClass();
		// 得到对象中所有的方法
		Method[] methods = c.getMethods();
		ArrayList<Method> setMethods = new ArrayList<Method>();
		// 得到对象中所有的属性
		Field[] fields = c.getFields();
		// 得到对象类的名字
		String cName = c.getName();
		// 从类的名字中解析出表名
		String tableName = cName.substring(cName.lastIndexOf(".") + 1,
				cName.length()).toLowerCase();
		List<String> mList = new ArrayList<String>();
		List vList = new ArrayList();
		for (Method method : methods) {
			String mName = method.getName();
			if (mName.startsWith("set") && !mName.startsWith("setClass")) {
				String fieldName = mName.substring(3, mName.length())
						.toLowerCase();
				mList.add(fieldName);
				setMethods.add(method);
				try {
					// method.invoke(object, null);
					//System.out.println(method.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
