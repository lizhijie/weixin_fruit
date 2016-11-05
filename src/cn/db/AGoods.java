package cn.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;     


public class AGoods {
public int id;
public String name;
public String imgurl;
public String about;
public int price;
public static int insert(AGoods agoods,Connection conn) {
	PreparedStatement pst;
	int i;
	String sql = "insert into goods (name,imgurl,about,price) values(?,?,?,?)";
    try {
        pst = (PreparedStatement) conn.prepareStatement(sql);
        pst.setString(1, agoods.name);
        pst.setString(2, agoods.imgurl);
        pst.setString(3, agoods.about);
        pst.setInt(4, agoods.price);
        i = pst.executeUpdate();
        return i;
    } catch (SQLException e) {
        e.printStackTrace();
    }
	return 0;
}
public static ResultSet find(Connection conn)
{
	String sql="select *from goods";
	 try {  
     	
        PreparedStatement pst = conn.prepareStatement(sql);//×¼±¸Ö´ÐÐÓï¾ä  
     	return pst.executeQuery();
     } catch (Exception e) {  
         e.printStackTrace();
     }
		return null;  	
}
public static int delete(AGoods agoods,Connection conn)
{
	int i = 0;
	String by="";
	String text="";
	
    String sql = "delete from goods where col15516937925 = 'value1157585903'" ;
    PreparedStatement pst;
    try {
        
        if(agoods.name!=null&&agoods.name!="")
        {
    		by="name";
    		text=agoods.name;
    		
    	}
        else if(agoods.id==0&&agoods.id!=0)
        {
    		by="id";
    		text=agoods.id+"";
        }
    	else if(agoods.imgurl!=null&&agoods.imgurl!="")
    	{
    		by="imgurl";
    		text=agoods.imgurl;
    	}
    	else if(agoods.about!=null&&agoods.about!="")
    	{
    		by="about";
    		text=agoods.about;
    	}
    	else if(agoods.price!=0&&agoods.price!=0)
    	{
    		by="price";
    		text=agoods.price+"";
    	}
        sql=sql.replace("col15516937925", by);
        sql=sql.replace("value1157585903", text);
        pst = (PreparedStatement) conn.prepareStatement(sql);
        System.out.println(sql);
        i = pst.executeUpdate();
        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
public static int update(AGoods agoods,Connection conn,AGoods set)
{
	int i = 0;
	String by="";
	String text="";
	String strSet="";
	int f=0;
	
    String sql = "update goods setset15839499880 where col15516937925 = 'value1157585903'" ;
    PreparedStatement pst;
    try {
        
        if(agoods.name!=null&&agoods.name!="")
        {
    		by="name";
    		text=agoods.name;
    		
    	}
        else if(agoods.id==0&&agoods.id!=0)
        {
    		by="id";
    		text=agoods.id+"";
        }
    	else if(agoods.imgurl!=null&&agoods.imgurl!="")
    	{
    		by="imgurl";
    		text=agoods.imgurl;
    	}
    	else if(agoods.about!=null&&agoods.about!="")
    	{
    		by="about";
    		text=agoods.about;
    	}
    	else if(agoods.price!=0&&agoods.price!=0)
    	{
    		by="price";
    		text=agoods.price+"";
    	}
        if(set.name!=null&&set.name!="")
        {
    			strSet+=" name = "+set.name+"";
    		f++;
    	}
        if(set.id==0&&set.id!=0)
        {
    		if(f!=0)
    			strSet+=",";
    			strSet+=" id = '"+set.id+"'";
    		f++;
        }
    	if(set.imgurl!=null&&set.imgurl!="")
    	{
    		if(f!=0)
    			strSet+=",";
    			strSet+=" imgurl = '"+set.imgurl+"'";
    		f++;
    	}
    	if(set.about!=null&&set.about!="")
    	{
    		if(f!=0)
    			strSet+=",";
    			strSet+=" about = '"+set.about+"'";
    		f++;
    	}
    	if(set.price!=0&&set.price!=0)
    	{
    		if(f!=0)
    			strSet+=",";
    			strSet+=" price = '"+set.price+"'";
    		f++;
    	}
        sql=sql.replace("set15839499880", strSet);
        sql=sql.replace("col15516937925", by);
        sql=sql.replace("value1157585903", text);
        pst = (PreparedStatement) conn.prepareStatement(sql);
        System.out.println(sql);
        i = pst.executeUpdate();
        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
}
}
