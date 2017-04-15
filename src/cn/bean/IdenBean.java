package cn.bean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdenBean extends Bean{

	public IdenBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	private String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	String echostr;
	public void go() throws IOException
	{
		setEchostr(request.getParameter("echostr")); 
		 if (echostr != null && echostr.length() > 1) {  
			 
				 response.getWriter().print(getEchostr());
				 
			 
	//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx778e2410cfa1d2c9&redirect_uri=https%3A%2F%2Flizhijie.tunnel.2bdata.com%2Fweixin%2Findex.jsp&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect
	}
}
}
