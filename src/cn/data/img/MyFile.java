package cn.data.img;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface MyFile{
public InputStream get(String fileName);
public boolean upload(InputStream in,String fileName);
public boolean delete(String fileName);
public void setSrc(String src);
public String getSrc();
public boolean rename(String oldname,String newname);
}
