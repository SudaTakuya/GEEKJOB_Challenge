<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
</head>
<body>
	<h1>エラーが発生しました</h1>
	<p><%=request.getAttribute("error") %>
	<br><a href="/RegisterSystem/register.jsp">戻る</a>
</body>
</html>