package cn.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.business.Shop;
import cn.data.img.FilePost;
import cn.data.table.Goods;
import cn.debug.MyDebug;

public class AdminBean extends FileBean {
	Shop shop;
	public AdminBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		super.init();
		setUserId(login());
		shop=new Shop(getUserId());
		return true;
	}
	public void go(){
		Gson gson=new Gson();
		String delAlias=request.getParameter("delGoods");
		if(request.getParameter("upload")!=null)
		{
		upload();
		}
		else if(request.getParameter("updateGoods")!=null)
		{
		updateGoods();
		}
		else if(delAlias!=null&&!("".contentEquals(delAlias)))
				{
			int i=shop.delGoods(delAlias);
			int a[]={0};
			if(i>0)a[0]=i;
			if(i>0)
			{
				file.delete(delAlias+".jpg");
			file.delete(delAlias+"-des.jpg");
			}
			setJson(gson.toJson(a));
			}
		
		
	}
	public void upload()
	{
		FilePost post=null;
		try {
			int tempbyte=0;
			InputStream in=request.getInputStream();
			post=new FilePost(in,file);
	//		post.getHead();
//			String li="";
//			MyDebug.println(this,post.spit);
//			MyDebug.println(this,post.Head());
//			li=post.content();
//			li=li.replace("\r", "-r");
//			li=li.replace("\n", "-n");
//			MyDebug.println(this,li);
//			byte[] b = new byte[4096];
//			while((tempbyte=in.read(b)) != -1){
//			System.out.write(b,0,tempbyte);
//			}
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Goods goods=new Goods();
		goods.setAlias(post.getPost("alias").get(0));
		goods.setName(post.getPost("name").get(0));
		goods.setAbout(post.getPost("about").get(0));
		goods.setDesxml(post.getPost("desxml").get(0));
		goods.setPrice(Integer.parseInt(post.getPost("price").get(0)));
		goods.setStatus(1);
		goods.setGroups("default");
		goods.setTime(shop.getSafeClerk().scanTime());
		shop.addGoods(goods);
		overWrite(post.getPost("pic"),goods.getAlias()+".jpg");
		overWrite(post.getPost("pic_des"),goods.getAlias()+"-des.jpg");
		//MyDebug.println(this, pic.contentEquals("")+"");
		MyDebug.println(this, "very Good");
	}
	
	public void updateGoods()
	{
		FilePost post=null;
		try {
			int tempbyte=0;
			InputStream in=request.getInputStream();
			post=new FilePost(in,file);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Goods goods=new Goods();
		goods.setAlias(post.getPost("alias").get(0));
		goods.setName(post.getPost("name").get(0));
		goods.setAbout(post.getPost("about").get(0));
		goods.setDesxml(post.getPost("desxml").get(0));
		goods.setPrice(Integer.parseInt(post.getPost("price").get(0)));
		goods.setStatus(1);
		goods.setGroups("default");
		goods.setTime(shop.getSafeClerk().scanTime());
		shop.updateGoods(goods);
		overWrite(post.getPost("pic"),goods.getAlias()+".jpg");
		overWrite(post.getPost("pic_des"),goods.getAlias()+"-des.jpg");
		MyDebug.println(this, "very Good");
	}
	
	boolean overWrite(ArrayList<String> from,String fileName)
	{
		if(from!=null)
			if(from.size()>0)
				if(from.get(0)!=null&&!("".contentEquals(from.get(0))))
				{
					file.delete(fileName);
					file.rename(from.get(0),fileName);
				}
		return true;
	}

}
