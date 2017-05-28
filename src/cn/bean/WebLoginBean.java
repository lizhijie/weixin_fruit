package cn.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cn.business.SafeClerk;
import cn.business.Shop;
import cn.data.table.Receiver;
import cn.debug.MyDebug;

public class WebLoginBean extends Bean {

	public WebLoginBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
public void go()
{
	Gson gson = new Gson();
	String loginName=request.getParameter("loginName");
	String passwd=request.getParameter("passwd");
	String creatUser=request.getParameter("creatUser");
	String logout=request.getParameter("logout");
	if(logout!=null)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
	}
	else if(creatUser!=null&&loginName!=null&&passwd!=null)
	{
		SafeClerk.creatWebUser(creatUser, loginName, passwd);
	}
	else if(loginName!=null&&passwd!=null)
	{
		MyDebug.println(this,loginName);
		MyDebug.println(this,passwd);
	String userId=SafeClerk.webLoginchange(loginName,passwd);
	if(userId!=null)
	{
	HttpSession session = request.getSession();
	session.setAttribute("userId", userId);
	fly("index.jsp","Index",false);
	}
	}
	else
	{
	//setJson(gson.toJson(rec));
	}
	
}
}
