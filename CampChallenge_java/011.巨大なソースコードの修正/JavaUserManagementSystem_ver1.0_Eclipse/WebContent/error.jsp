<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--課題1 JumsHelperクラスのインポートを追加 --%>
<%@ page import="jums.JumsHelper"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%>
        <%--課題1 JumsHelperクラスのhome()メソッドを用いてトップへのリンクを追加 --%>
        <br><%= JumsHelper.getInstance().home() %>
    </body>
</html>
