package cn.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.business.Shop;
import cn.data.table.Receiver;

import com.google.gson.Gson;

public class MineBean extends Bean {

	public MineBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	public void go(){
		try {
			super.go();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Shop shop = new Shop(getUserId());
		Gson gson = new Gson();
		String recname=request.getParameter("recname");
		String recnum=request.getParameter("recnum");
		String recaddress=request.getParameter("recaddress");
		if(recname!=null&&recnum!=null&&recaddress!=null)
		{
			setJson(gson.toJson(shop.updateDefaultRec(recname,recnum,recaddress)));
		}
		else
		{
		Receiver rec=shop.defalutRec(); 
		setJson(gson.toJson(rec));
		}
		
	}
}
