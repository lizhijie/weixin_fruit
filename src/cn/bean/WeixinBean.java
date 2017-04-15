package cn.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.weixin.Message;
import cn.weixin.MsgSys;
import cn.weixin.Weixin;


public class WeixinBean extends Bean {

	public WeixinBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	public void go(){
	Weixin weixin=new Weixin(request,response);
	Message ms=weixin.msgSys.msg();
	System.out.println(ms.getContent());
	String t=ms.getFromUserName();
	ms.setFromUserName(ms.getToUserName());
	ms.setToUserName(t);
	//ms.setContent("¿Ó÷æΩ‹");
	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String str=format.format(date);
	  System.out.println(str);
	  ms.setContent(str+ms.getContent());
	weixin.msgSys.answerText(ms);
	System.out.println(weixin.msgSys.answerText(ms));
	}

}
