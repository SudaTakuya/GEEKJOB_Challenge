<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.ItemDTO" %>
<!DOCTYPE html>
<%
ItemDTO itemDTO = (ItemDTO)request.getAttribute("itemDTO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
</head>
<body>
	<h1>商品の登録が完了しました</h1>
	<p>商品名：<%=itemDTO.getName() %><br>
	   値段：<%=itemDTO.getPrice() %>円</p><br><br>

	<br><a href="/RegisterSystem/menu.jsp">メニューに戻る</a>


</body>
</html>