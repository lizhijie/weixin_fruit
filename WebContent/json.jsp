<%
	long startTime = System.currentTimeMillis();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="index" class="cn.bean.Control" scope="request" /><%@page import="cn.debug.*"%>
<%index.make(request, response);%>
${index.bean.json}
<%
	long endTime = System.currentTimeMillis();MyDebug.println(this,"json程序运行时间："+(endTime-startTime)+"ms");
%>