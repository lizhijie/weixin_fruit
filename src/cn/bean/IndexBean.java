package cn.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.business.*;
import cn.data.database.DataBase;
import cn.data.table.*;
import cn.weixin.Weixin;

public class IndexBean extends Bean {
	public IndexBean(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	String name;
	String userId;
	String userName;
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
//		InputStream in=request.getInputStream();
//		int tempbyte;
//		byte[] b = new byte[4096];
//		while((tempbyte=in.read(b)) != -1){
//		System.out.write(b,0,tempbyte);
//		}
		this.setName(request.getParameter("li"));
		//DataBase.status=false;
		String alias=request.getParameter("alias");
		setUserId(login());
		Shop shop = new Shop(getUserId());
		setUserName(shop.getSafeClerk().getUserName());
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
		//MyDebug.println(this,"lizhijie");
		//long startTime = System.currentTimeMillis();
		//long endTime = System.currentTimeMillis();MyDebug.println(this,"程序运行时间："+(endTime-startTime)+"ms");
	}

	public String getUserName() {
		if(userName!=null)
			if(userName!="")
				return userName;
		return "未设置";
		
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		super.init();
		if(Weixin.check(request))
		{
			try {
				request.getRequestDispatcher("/file.jsp?pages=Weixin").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			try {
				this.request.getRequestDispatcher("/file.jsp?pages=Iden").forward(request,
						response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

}
