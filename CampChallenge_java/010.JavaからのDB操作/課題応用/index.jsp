<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録システム</title>
</head>
<body>
	<h1>ログイン</h1>
	<p>ユーザー名、パスワードを入力してください。</p>
	<form action="/RegisterSystem/Login" method="post">
	ユーザー名 : <input type="text" name="name"><br><br>
	パスワード : <input type="passward" name="passward"><br><br>

	<input type="submit" value="ログイン">
	</form>

</body>
</html>