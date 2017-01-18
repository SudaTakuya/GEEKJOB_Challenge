<%@page import="javax.servlet.http.HttpSession" %>
<%--課題1 JumsHelperクラスのインポートを追加 --%>
<%--課題3 UserDataBeansクラスのインポートを追加 --%>
<%@ page import="jums.JumsHelper,jums.UserDataBeans"%>

<%
    HttpSession hs = request.getSession();
	//課題３ UserDataBeansインスタンスをセッションより取得
	UserDataBeans userDataBeans = (UserDataBeans)hs.getAttribute("userDataBeans");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
    <%--課題３ UserDataBeansを利用した処理に変更 --%>

    <% if(!userDataBeans.getName().equals("")){ %>
    	<h1>登録確認</h1>
    	名前:<%= userDataBeans.getName() %><br>
    	生年月日:<%= userDataBeans.getYear()+"年"+userDataBeans.getMonth()+"月"+userDataBeans.getDay()+"日"%><br>
    	種別:<%= userDataBeans.getType() %><br>
    	電話番号:<%= userDataBeans.getTell() %><br>
    	自己紹介:<%= userDataBeans.getComment() %><br>

	<%--
    <% if(!hs.getAttribute("name").equals("")){ %>
        <h1>登録確認</h1>
        名前:<%= hs.getAttribute("name")%><br>
        生年月日:<%= hs.getAttribute("year")+"年"+hs.getAttribute("month")+"月"+hs.getAttribute("day")+"日"%><br>
        種別:<%= hs.getAttribute("type")%><br>
        電話番号:<%= hs.getAttribute("tell")%><br>
        自己紹介:<%= hs.getAttribute("comment")%><br>
	--%>

        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
        <%--課題２  セッションから不正アクセス確認用コード"ac"を取得し、InsertResurtに非表示でリクエストする--%>
        	<input type="hidden" name="ac" value=<%= hs.getAttribute("ac") %>>
            <input type="submit" name="yes" value="はい">
        </form>
    <% }else{ %>
        <h1>入力が不完全です</h1>
    <% } %>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
        <%--課題1 JumsHelperクラスのhome()メソッドを用いてトップへのリンクを追加 --%>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
