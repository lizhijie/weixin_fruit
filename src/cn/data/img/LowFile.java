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
		//MyDebug.println(this,"·����+"+file.getAbsolutePath());
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
		    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
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
        if(!oldname.equals(newname)){//�µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ���������� 
            File oldfile=new File(getSrc()+oldname); 
            File newfile=new File(getSrc()+newname); 
            if(!oldfile.exists()){
                return false;//�������ļ�������
            }
            if(newfile.exists())//���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ�������������� 
            {
                MyDebug.println(this,newname+"�Ѿ����ڣ�"); 
            return false;
            }
            else{ 
                return oldfile.renameTo(newfile); 
            } 
        }else{
            MyDebug.println(this,"���ļ����;��ļ�����ͬ...");
            return false;
        }
    }
}
