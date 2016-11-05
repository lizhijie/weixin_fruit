package cn.bean;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import cn.db.*;

public class IndexBean extends Bean {
	public IndexBean() {
		super();

	}

	String name;
	ArrayList<AGoods> agoods;
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<AGoods> getAgoods() {
		return agoods;
	}

	public void setAgoods(ArrayList<AGoods> agoods) {
		this.agoods = agoods;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void go(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException {
		this.setName(request.getParameter("li"));
		String echostr = request.getParameter("echostr");
		if (echostr != null && echostr.length() > 1) {
			request.getRequestDispatcher("/view/iden.jsp").forward(request,
					response);

		}
		ArrayList<AGoods> agoods = new ArrayList<AGoods>();
		Data data = new Data();
		//data.add();
		data.getAllGoods(agoods);
		String userid = login(request, response);
		setAgoods(agoods);
		setName(name);
		setUserId(userid);
		login(request, response);

	}
}
