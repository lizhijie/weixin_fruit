package cn.bean;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.data.img.LowFile;
import cn.data.img.MyFile;

public class FileBean extends Bean {
	MyFile file;
	public FileBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub

	}
	public boolean init(){
		file=new LowFile();
		file.setSrc(request.getSession().getServletContext().getRealPath("upload"));
		return true;
	}
	public static boolean isValidFileName(String fileName) { if (fileName == null || fileName.length() > 255) return false; else return fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$"); }

	public void go()
	{
		String imgName=request.getParameter("getimg");
		if(imgName!=null)
			if(imgName!="")
				if(!isValidFileName(imgName))
				{
					try {
						response.getOutputStream().println("fileName error");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
		
		InputStream in=file.get(imgName);
		int tempbyte;
		try {
			
//			while ((tempbyte = in.read()) != -1) {
//			    //MyDebug.println(this,(char)tempbyte+" ");
//			    response.getOutputStream().write(tempbyte);
//			}
//			
			
//			byte[] b = new byte[in.available()];
//			in.read(b); //首先把in的内容读到字节数组b里面
//			response.getOutputStream().write(b);
			
			
			byte[] b = new byte[4096];
			while((tempbyte=in.read(b)) != -1){
			response.getOutputStream().write(b,0,tempbyte);
			}
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
