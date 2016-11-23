package cn.bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.business.*;
import cn.data.database.DataBase;
import cn.data.table.*;

public class IndexBean extends Bean {
	public IndexBean(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	String name;
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String GoodsId) {
		this.userId = GoodsId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void go() throws ServletException, IOException {
		//long startTime = System.currentTimeMillis();
		this.setName(request.getParameter("li"));
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			this.request.getRequestDispatcher("/view/iden.jsp").forward(request,
					response);
		}
		//DataBase.status=false;
		String alias=request.getParameter("alias");
		setUserId(login());
		Shop shop = new Shop(getUserId());
		ArrayList<Goods> aGoods = new ArrayList<Goods>();
		if(alias!=null)
		{
			Goods goods=new Goods();
			goods=shop.getAGoods(alias);
			Gson gson = new Gson(); 
			setJson(gson.toJson(goods));
		}
		else{
		aGoods=shop.index();
		Gson gson = new Gson(); 
		setJson(gson.toJson(aGoods));
		}
		//System.out.println("lizhijie");
		//long startTime = System.currentTimeMillis();
		//long endTime = System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
	}
}
