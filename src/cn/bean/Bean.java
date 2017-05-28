package cn.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.debug.MyDebug;
import cn.weixin.Authorization;

public class Bean {
	String json = "";
	HttpServletRequest request;
	HttpServletResponse response;
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

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
		if(init())
		{
		try {
			go();
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}


	public boolean init() {
		// TODO Auto-generated method stub
		return true;
	}


	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void fly(String entry,String pages,boolean forward)
	{
		String pages_head="";
		if(pages!=null)
			pages_head="?pages=";
			else
				pages="";
		String url="/"+entry+pages_head+pages;
		if(forward)
		{
		try {
			request.getRequestDispatcher(url).forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			try {
				response.sendRedirect("."+url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String login() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj != null) {
			userId = obj.toString();
			MyDebug.println(this,userId);
			return userId;
		}
		String s = request.getParameter("code");
		if (s == null || request.getParameter("code") == "") {
			;
		} else {
			Authorization auth = new Authorization(s);
			userId = auth.getOpenid();
			session.setAttribute("userId", userId);
			MyDebug.println(this,"new" + userId);
			return userId;
		}
 userId="guest";
		return userId;
	}


	public void go()
			throws ServletException, IOException {
		setUserId(login());
		MyDebug.println(this,request.getParameter("echostr"));
	}

}
