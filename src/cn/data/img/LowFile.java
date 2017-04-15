package cn.data.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.debug.MyDebug;

public class LowFile implements MyFile{

	static String fileSrc="../file/";
	@Override
	public InputStream get(String fileName) {
		// TODO Auto-generated method stub
		fileName=fileSrc+fileName;
		File file = new File(fileName);
		//MyDebug.println(this,"路径是+"+file.getAbsolutePath());
	        InputStream in = null;
	        try {
	            
	            in = new FileInputStream(file);
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	     return in;
	}

	@Override
	public boolean upload(InputStream in,String fileName) {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			 int tempbyte;
	            try {
					while ((tempbyte = in.read()) != -1) {
					    //System.out.print((char)tempbyte+" ");
					    out.write(tempbyte);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String fileName) {
		// TODO Auto-generated method stub
		boolean  flag = false;  
		   File file = new File(getSrc()+fileName);  
		    // 路径为文件且不为空则进行删除  
		    if (file.isFile() && file.exists()) {  
		        file.delete();  
		        flag = true;  
		    }  
		    return flag; 
	}
	public void setSrc(String src){
		if(src.charAt(src.length()-1)=='\\'|| src.charAt(src.length()-1)=='/')
		fileSrc=src;
		else
			fileSrc=src+"/";
	}
	public String getSrc(){
		return fileSrc;
		
	}

	public boolean rename(String oldname,String newname){ 
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(getSrc()+oldname); 
            File newfile=new File(getSrc()+newname); 
            if(!oldfile.exists()){
                return false;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            {
                MyDebug.println(this,newname+"已经存在！"); 
            return false;
            }
            else{ 
                return oldfile.renameTo(newfile); 
            } 
        }else{
            MyDebug.println(this,"新文件名和旧文件名相同...");
            return false;
        }
    }
}
