package cn.bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;

import cn.db.*;

public class IndexBean extends Bean {
	public IndexBean(HttpServletRequest request, HttpServletResponse response) {
		super(request,response);
	}
	String name;
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void go() throws ServletException, IOException {
		this.setName(request.getParameter("li"));
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			this.request.getRequestDispatcher("/view/iden.jsp").forward(request,
					response);
		}
		ArrayList<AGoods> agoods = new ArrayList<AGoods>();
		Data data = new Data();
		setUserId(login());
		agoods=data.getGoods();
		Gson gson = new Gson(); 
		setJson(gson.toJson(agoods));
		//System.out.println("lizhijie");
	}
}
