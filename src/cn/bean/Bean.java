package cn.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.weixin.Authorization;

public class Bean {
	String json = "";
	HttpServletRequest request;
	HttpServletResponse response;
	String userId=null;
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Bean(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request=request;
		this.response=response;
		try {
			go();
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String login() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj != null) {
			userId = obj.toString();
			System.out.println(userId);
			return userId;
		}
		String s = request.getParameter("code");
		if (s == null || request.getParameter("code") == "") {
			;
		} else {
			Authorization auth = new Authorization(s);
			userId = auth.getOpenid();
			session.setAttribute("userId", userId);
			System.out.println("new" + userId);
		}
		return userId;
	}


	public void go()
			throws ServletException, IOException {
		setUserId(login());
	}
}
