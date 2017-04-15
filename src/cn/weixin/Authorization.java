package cn.weixin;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.weixin.HttpRequest;

public class Authorization {
String  access_token;
String  expires_in;
String refresh_token;
String openid;
String scope;
public String getAccess_token() {
	return access_token;
}



public void setAccess_token(String access_token) {
	this.access_token = access_token;
}



public String getExpires_in() {
	return expires_in;
}



public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}



public String getRefresh_token() {
	return refresh_token;
}



public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}



public String getOpenid() {
	return openid;
}



public void setOpenid(String openid) {
	this.openid = openid;
}



public String getScope() {
	return scope;
}



public void setScope(String scope) {
	this.scope = scope;
}







	public Authorization(String code) {
        //发送 GET 请求
        String s=HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", "appid=wx778e2410cfa1d2c9&secret=d0681fd3fcab27013cd72e3da7758666&code="+code+"&grant_type=authorization_code");
       
        this.setAccess_token(parseData(s).get("access_token"));
        this.setExpires_in(parseData(s).get("expires_in"));
        this.setOpenid(parseData(s).get("openid"));
        this.setRefresh_token(parseData(s).get("refresh_token"));
        this.setScope(parseData(s).get("scope"));
        //System.out.println(parseData(s).get("openid"));
        
        //发送 POST 请求
       //String sr=HttpRequest.sendPost("http://localhost:8080/weixin", "key=123&v=456");
       //System.out.println(sr);
	}
	
	private static Map<String, String> parseData(String data){
	       GsonBuilder gb = new GsonBuilder();
	       Gson g = gb.create();
	       Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
	       return map;
	   }
}
