<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
</head>
<body>
	<h1>商品登録</h1>
	<form action="/RegisterSystem/Register" method="post">
		名前：<input type="text" name="name"><br>
		値段：<input type="text" name="price"><br><br>

		<input type="submit" value="登録">
	</form>

	<br><a href="/RegisterSystem/menu.jsp">メニューに戻る</a>
</body>
</html>