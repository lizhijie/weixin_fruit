package cn.bean;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.business.Shop;
import cn.data.table.Buybus;

import com.google.gson.Gson;

public class BuyBusBean extends Bean {
	
	public BuyBusBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	public void go(){
		Gson gson = new Gson(); 
		String name=login();
		Shop shop=new Shop(name);
		String aliasCon=request.getParameter("delAlias");
		String addAlias=request.getParameter("addAlias");
		String countPlus=request.getParameter("countPlus");
		String countAlias=request.getParameter("countAlias");
		if(aliasCon!=null){
			boolean h=shop.delBuybus(aliasCon);
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		}else if(addAlias!=null){
			boolean h=shop.addBuyBus(addAlias);
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		}
		else if((countAlias!=null)&&(countPlus!=null)){
			boolean h=shop.countBuybus(countAlias,countPlus);
			int a[]={0};
			if(h)a[0]=1;
			setJson(gson.toJson(a));
		}
		else{
		ArrayList<Buybus> buybus = new ArrayList<Buybus>();
		buybus=shop.buyBus();
		setJson(gson.toJson(buybus));
		}
		

}

}