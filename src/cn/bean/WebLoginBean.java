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
	String logout=request.getParameter("logout");
	String register=request.getParameter("register");

	if(register!=null&&check(loginName)&&check(passwd)&&checkVcode())
	{
		String userId=null;
		userId=login();
		MyDebug.println(this, userId);
		if(userId.trim().contentEquals("guest"))
			userId=null;
		int i=SafeClerk.creatWebUser(userId, loginName, passwd);
		setJson(i+"");
	}
	else if(logout!=null)
	{
		MyDebug.println(this,"li");
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
	}
	else if(check(loginName)&&check(passwd)&&checkVcode())
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
	setJson("用户名或着密码不正确");
	checkVcode();
	}
	
}
boolean checkVcode()
{
	String vcode=request.getParameter("vcode");
	HttpSession sess = request.getSession();
	Object ob=sess.getAttribute("vcode");
	String localVcode=null;
	if(ob!=null)
	localVcode=ob.toString();
	MyDebug.println(this, localVcode);
	if(check(vcode)&&check(localVcode))
		if(vcode.contentEquals(localVcode))
			return true;
	setJson("验证码错误");
	return false;
}
}
