package cn.bean;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.business.Shop;
import cn.data.table.Orders;

public class AddressBean extends Bean {

	public AddressBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	public void go(){
		String name=login();
		Gson gson = new Gson(); 
		Shop shop=new Shop(name);
		String recAddress=request.getParameter("recaddress");
		String recNum=request.getParameter("recnum");
		String recName=request.getParameter("recname");
		String updateAddress=request.getParameter("updateaddress");
		//System.out.println(recAddress+"---"+recNum+"-----"+recName);
		String str=request.getParameter("delNum");
		String buy=request.getParameter("buy");
		String ordersNum=request.getParameter("num");
		if(str==null)
			str="0";
		int num=Integer.parseInt(str);
		if(ordersNum==null)
			ordersNum="0";
		int myNum=Integer.parseInt(ordersNum);
		if(updateAddress!=null)
		{
			int a[]={0};
			if(!(recNum==null&&recNum==null&&recName==null))
			{
			ArrayList<String> rec=new ArrayList<String>();
			rec.add(recName);
			rec.add(recNum);
			rec.add(recAddress);
				int h=shop.updateCoolAddress(rec, myNum);
				if(h>0)a[0]=h;
			}
			setJson(gson.toJson(a));
		}
		else if(num!=0)
		{
			boolean h=shop.delOrders(num);
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		
		}
		else if(myNum!=0)
		{
			ArrayList<Object> orders = new ArrayList<Object>();
			orders=shop.getAOrders(myNum);
			setJson(gson.toJson(orders));
		}
		else if(buy!=null)
		{
			int h=shop.toOrders();
			int a[]={0};
			if(h>0)a[0]=h;
			setJson(gson.toJson(a));
		}
		else
		{
			ArrayList<Orders> orders = new ArrayList<Orders>();
			ArrayList<Object> obj = new ArrayList<Object>();
			obj=shop.orders();
			orders=(ArrayList<Orders>) obj.get(0);
			Map map = new HashMap();
			for(int i=0;i<orders.size();i++)
			{
			if(map.get(orders.get(i).getNum())==null)
			{
			ArrayList<Orders> temp = new ArrayList<Orders>();
			temp.add(orders.get(i));
			map.put(orders.get(i).getNum(),temp);
			}else
			{
				ArrayList<Orders> temp = (ArrayList<Orders>) map.get(orders.get(i).getNum());
				temp.add(orders.get(i));
			}
			}
			obj.set(0, map);
			setJson(gson.toJson(obj));
		}
		
	}
	

}
