<%@page import="cn.weixin.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="index" class="cn.bean.IndexBean" scope="request" />
<%index.go(request, response);%>
${index.json}