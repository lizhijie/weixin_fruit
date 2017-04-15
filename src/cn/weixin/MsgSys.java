package cn.weixin;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.el.parser.SimpleNode;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class MsgSys {
	HttpServletRequest request;
	HttpServletResponse response;
	public MsgSys(HttpServletRequest request,HttpServletResponse response)
	{
		this.request=request;
		this.response=response;
	}
	public  Message changeMsg(String xml) throws ParserConfigurationException, SAXException, IOException
	{
		Message message=new Message();
		//String su=new String(xml.getBytes());
		String su=xml;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			StringReader sr = new StringReader(su);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			System.out.println(su); 
			 Document doc=db.parse(is);
			// System.out.println(doc.getElementsByTagName("Content").item(0).getTextContent());
		message.setContent(doc.getElementsByTagName("Content").item(0).getTextContent());
		message.setFromUserName(doc.getElementsByTagName("FromUserName").item(0).getTextContent());
		message.setToUserName(doc.getElementsByTagName("ToUserName").item(0).getTextContent());
		message.setMsgType(doc.getElementsByTagName("MsgType").item(0).getTextContent());
		message.setCreateTime(Long.parseLong(doc.getElementsByTagName("CreateTime").item(0).getTextContent()));
		message.setMsgId(Long.parseLong(doc.getElementsByTagName("MsgId").item(0).getTextContent()));
		
		return message;
	}
	public String answerMsg(Message msg,Document doc)
	{
		String t=msg.ToUserName;
		msg.ToUserName=msg.FromUserName;
		msg.FromUserName=t;
		
		return "";
		
	}
	 ByteArrayInputStream getStringStream(String sInputString){
		if (sInputString
		 != null &&
		 !sInputString.trim().equals("")){
		try{
		ByteArrayInputStream
		 tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
		return tInputStringStream;
		}catch (Exception
		 ex){
		ex.printStackTrace();
		}
		}
		return null;
		}
	public  String getXml(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");
		BufferedReader in = request.getReader();
		int k=0;
		String s="";
		while(k!=-1)
		{
			k=in.read();
			if(k!=-1)
			s=s+(char)(k);
			System.out.print((char)k+"-");
		}
		return s;
	}
	public  Message msg()
	{
		String xml=null;
		Message ms=null;
		try {
			xml=getXml(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ms=changeMsg(xml);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ms;
	}
	public  String answerText(Message ms){
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document doc=db.newDocument();
		Element xml = doc.createElement("xml");
		doc.appendChild(xml);
		Element ToUserName = doc.createElement("ToUserName");
		xml.appendChild(ToUserName);
		ToUserName.appendChild(doc.createCDATASection(ms.getToUserName()));

		Element FromUserName = doc.createElement("FromUserName");
		xml.appendChild(FromUserName);
		FromUserName.appendChild(doc.createCDATASection(ms.getFromUserName()));

		Element CreateTime = doc.createElement("CreateTime");
		xml.appendChild(CreateTime);
		CreateTime.setTextContent(ms.getCreateTime()+"");
		
		Element MsgType = doc.createElement("MsgType");
		xml.appendChild(MsgType);
		MsgType.appendChild(doc.createCDATASection(ms.getMsgType()));
		
		Element Content = doc.createElement("Content");
		xml.appendChild(Content);
		Content.appendChild(doc.createCDATASection(ms.getContent()));
		
         OutputFormat format = new OutputFormat(doc);
         format.setLineWidth(65);
         format.setIndenting(true);
         format.setIndent(2);
         Writer out = new StringWriter();
         XMLSerializer serializer = new XMLSerializer(out, format);
         try {
			serializer.serialize(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String ans=out.toString();
         try {
			response.getWriter().print(ans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return ans;

		
	}

}
