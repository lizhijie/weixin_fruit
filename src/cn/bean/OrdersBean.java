package cn.bean;

import java.lang.Integer;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.business.Shop;
import cn.data.table.Orders;

public class OrdersBean extends Bean {

	public OrdersBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	public void go(){
		String name=login();
		Gson gson = new Gson(); 
		Shop shop=new Shop(name);
		String str=request.getParameter("delNum");
		String buy=request.getParameter("buy");
		if(str==null)
			str="0";
		int num=0;
		num=Integer.parseInt(str);
		if(num!=0)
		{
			boolean h=shop.delOrders(num);
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		
		}
		else if(buy!=null)
		{
			boolean h=shop.toOrders();
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		}
		else
		{
			ArrayList<Orders> orders = new ArrayList<Orders>();
			orders=shop.orders();
			setJson(gson.toJson(orders));
		}
		
	}
	

}
