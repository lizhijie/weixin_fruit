package cn.bean;


import java.io.IOException;


import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import cn.db.*;

public class IndexBean extends Bean {
	public IndexBean()
	{
		super();
		
	}
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
public void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException{	
	this.setName(request.getParameter("li"));
		String echostr = request.getParameter("echostr"); 
		 if (echostr != null && echostr.length() > 1) {  
	            request.getRequestDispatcher("/view/iden.jsp").forward(request, response);
		
	}
		 ArrayList<AGoods> agoods=new ArrayList<AGoods>();
		 Data data=new Data();
		 data.getAllGoods(agoods);
		 String name="";
		 int i;
			for (i = 0; i < agoods.size() && agoods.get(i) != null; i++) {
				name+="<tr><td>"+agoods.get(i).id + "</td><td>"
						+ agoods.get(i).name + "</td><td>" + agoods.get(i).imgurl
						+ "</td><td>" + agoods.get(i).about + "</td><td>"
						+ agoods.get(i).price+"</td></tr>";
			}
			 String userid=login(request,response);
			name="<table>"+name+"</table>userid:"+userid+"<br>";
		 setName(name);
		 login(request,response);
		 
	        
}
}
