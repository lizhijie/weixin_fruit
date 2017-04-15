package cn.weixin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Weixin {
	String access_token="";
	String appid="wx778e2410cfa1d2c9";
	String secret="d0681fd3fcab27013cd72e3da7758666";
	HttpServletRequest request;
	HttpServletResponse response;
	
	public MsgSys msgSys;
	public Weixin(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
		msgSys=new MsgSys(request,response);
	}
	public void updateAccess_toke()
	{
		String access_token=HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", "grant_type=client_credential&appid="+appid+"&secret="+secret);
		setAccess_token(parseData(access_token).get("access_token"));
	}
	public Map<String, String> getUserinfo(String openId)
	{
		String userInfo=HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info" , "access_token="+getAccess_token()+"&openid="+openId+"&lang=zh_CN");
		return parseData(userInfo);
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	private static Map<String, String> parseData(String data){
	       GsonBuilder gb = new GsonBuilder();
	       Gson g = gb.create();
	       Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
	       return map;
	   }
	
	
	
	
	
	
	public static boolean check(HttpServletRequest request)
	{
		String agent=request.getHeader("user-agent");
		if(agent.length()<20)
			return true;
		else
			return false;
	}
}
