package cn.data.img;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.debug.MyDebug;

public class FilePost {
String fileName="";
InputStream postIn;
Map<String,ArrayList<String>> text;
boolean status;
public String spit="";
int last=0;
MyFile myFile=null;
public FilePost(InputStream postIn,MyFile file)
{
	this.myFile=file;
	status=true;
this.postIn=postIn;	
this.text = new HashMap<String,ArrayList<String>>();
this.go();
}
public void go()
{
	String head="";
	getHead();
	String con="";
	ArrayList<String> value=null;
	while(status)
	{
	head=Head();
	if(status)
	{
	con=content(fileName);
	value=text.get(head);
	if(value!=null)
		value.add(con);
	else
	{
		value=new ArrayList<String>();
		value.add(con);
		text.put(head, value);
	}
	}
			MyDebug.println(this,head+"---"+con);
	

	}
	//MyDebug.println(this,getPost("job").get(0));
}
public boolean getHead() {
	// TODO Auto-generated method stub
	int temp=0;
	while(temp!=((int)'\n'))
	{
		try {
			temp=postIn.read();
			if(temp=='\r')
			temp=postIn.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temp!=((int)'\n'))
		{
			spit=spit+((char)temp);
		}
			
	}
	return !spit.contentEquals("");
}
public String content(String fileName) {
	// TODO Auto-generated method stub
	boolean isSrc=!fileName.contentEquals("");
	String pop="";
	OutputStream out=null;
	if(isSrc)
	{
		fileName=fileName+"-"+System.currentTimeMillis();
	File file = new File(myFile.getSrc()+fileName);
	try {
		out= new FileOutputStream(file);
		pop=fileName;
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	String tempSpit='\n'+spit;
	tempSpit='\r'+tempSpit;
	int[] queue=new int[1000];
	int head=0;
	int tail=0;
	for(int i=0;i<tempSpit.length();i++)
	{
		try {
			queue[head]=postIn.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		head=(head+1)%1000;
	}
	byte[] li=tempSpit.getBytes();
	int i=0;
	while(true)
	{
		for(i=0;i<tempSpit.length();i++)
		{
		if(li[i]!=queue[(tail+i)%1000])
			break;
		}
		if(i>=tempSpit.length())
			break;
		if(isSrc)
		{
		try {
			out.write(queue[tail]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else
		{
	pop=pop+(char)queue[tail];
	//MyDebug.println(this,queue[tail]);
		}
	tail=(tail+1)%1000;
	try {
		queue[head]=postIn.read();
		head=(head+1)%1000;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	try {
		if(postIn.read()=='\r')
			postIn.read();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(isSrc)
	{
	try {
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	if(isSrc)
		return pop;
	return changeCode(pop);
}
public String Head() {
	// TODO Auto-generated method stub
	int temp=1000000;
	int last=temp;
	String head="";
	while(temp!=((int)'\n')||last!=((int)'\n'))
	{
		try {
			last=temp;
			temp=postIn.read();
			if(temp==-1)
			{
				status=false;
				return "";
			}
			if(temp=='\r')
			temp=postIn.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temp!=((int)'\n')||last!=((int)'\n'))
		{
			head=head+((char)temp);
			//MyDebug.println(this,temp);
		}
			
	}
	String name=findValue(head, "name");
	fileName=changeCode(findValue(head, "filename"));
	return changeCode(name);
}
public ArrayList<String> getPost(String textName)
{
	return text.get(textName);
}
private String findValue(String head,String key)
{
	String[] one=head.split("([^a-zA-Z])"+key+"=\\"+'"');
	if(one.length>1)
	{
	String[] two=one[1].split("\\"+'"');
	if(two!=null)
		return two[0];
	}
	return "";
}

public String changeCode(String str)
{
	
	try {
		return new String(str.getBytes("iso8859-1"), "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str;
}
}
