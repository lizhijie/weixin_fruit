package cn.data.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DataReflect {
String tableName;
List<String> mList;
List vList;
ArrayList<Method> setMethods;
Class c;
public DataReflect(Object object){
	c = object.getClass();
	// 得到对象中所有的方法
	Method[] methods = c.getMethods();
	setMethods = new ArrayList<Method>();
	// 得到对象中所有的属性
	Field[] fields = c.getFields();
	// 得到对象类的名字
	String cName = c.getName();
	// 从类的名字中解析出表名
	tableName = cName.substring(cName.lastIndexOf(".") + 1,
			cName.length()).toLowerCase();
	mList = new ArrayList<String>();
	vList = new ArrayList();
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
		else if (mName.startsWith("set") && !mName.startsWith("setClass")) {
			String fieldName = mName.substring(3, mName.length())
					.toLowerCase();
			setMethods.add(method);
			try {
				// method.invoke(object, null);
				//MyDebug.println(this,method.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
public String getTableName() {
	return tableName;
}
public void setTableName(String tableName) {
	this.tableName = tableName;
}
public List<String> getmList() {
	return mList;
}
public void setmList(List<String> mList) {
	this.mList = mList;
}
public List getvList() {
	return vList;
}
public void setvList(List vList) {
	this.vList = vList;
}
public ArrayList<Method> getSetMethods() {
	return setMethods;
}
public void setSetMethods(ArrayList<Method> setMethods) {
	this.setMethods = setMethods;
}
public Class getC() {
	return c;
}
public void setC(Class c) {
	this.c = c;
}
}
