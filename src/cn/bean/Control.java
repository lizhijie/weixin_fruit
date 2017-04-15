package cn.bean;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.weixin.Weixin;

public class Control {
	Bean bean;
	public Bean getBean() {
		return bean;
	}
	public void setBean(Bean bean) {
		this.bean = bean;
	}
	public void make(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String page=request.getParameter("pages");
		if(page==null||page=="")
			page="Index";
		Class<?> c;
		try {
			c = Class.forName("cn.bean."+page+"Bean");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			c=Class.forName("cn.bean.IndexBean");
		}
		  @SuppressWarnings("rawtypes")
		Class[] parameterTypes={HttpServletRequest.class,HttpServletResponse.class};   
		//根据参数类型获取相应的构造函数  
		  Constructor<?> constructor=c.getConstructor(parameterTypes);  
		//参数数组  
		  Object[] parameters={request,response};  
		//根据获取的构造函数和参数，创建实例  
		  this.bean=(Bean) constructor.newInstance(parameters); 
}
}
