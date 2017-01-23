<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,db.ItemDTO" %>
<%
ArrayList<ItemDTO> list = (ArrayList<ItemDTO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録システム</title>
</head>
<body>
	<h1>商品一覧</h1>
	<table border="1">
		<tr bgcolor="silver">
			<th>商品名</th>
			<th>値段</th>
		</tr>
		<% for(int i = 0; i < list.size(); i++){ %>
		<tr>
			<td><%= list.get(i).getName() %></td>
			<td><%= list.get(i).getPrice() %></td>
		</tr>
		<% } %>
	</table>

	<br><br><a href="/RegisterSystem/menu.jsp">メニューに戻る</a>
</body>
</html>