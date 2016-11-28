<%
	long startTime = System.currentTimeMillis();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="index" class="cn.bean.Control" scope="request" />
<%index.make(request, response);%>
${index.bean.json}
<%
	long endTime = System.currentTimeMillis();System.out.println("程序运行时间："+(endTime-startTime)+"ms");
%>