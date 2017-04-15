<%
	long startTime = System.currentTimeMillis();
%><%@ page language="java" contentType="image/jpeg; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="cn.data.img.*"%><%@page import="cn.debug.*"%><%@page import="java.io.*"%><jsp:useBean id="index" class="cn.bean.Control" scope="request" /><%index.make(request, response);%><%
	long endTime = System.currentTimeMillis();MyDebug.println(this,"file 程序运行时间是"+(endTime-startTime)+"ms");
%>