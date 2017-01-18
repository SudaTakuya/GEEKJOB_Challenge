<%@page import="javax.servlet.http.HttpSession" %>
<%--課題1 JumsHelperクラスのインポートを追加 --%>
<%--課題３ UserDataBeansクラスのインポートを追加 --%>
<%@ page import="jums.JumsHelper,jums.UserDataBeans"%>
<%
    HttpSession hs = request.getSession();
	//課題３ UserDataBeansクラスのインスタンスをセッションより取得
	UserDataBeans userDataBeans =(UserDataBeans) hs.getAttribute("userDataBeans");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録結果画面</title>
    </head>
    <body>
        <h1>登録結果</h1><br>
        <%--課題３ UserDataBeansを用いた処理に変更 --%>
        名前:<%= userDataBeans.getName() %><br>
        生年月日:<%= userDataBeans.getYear()+"年"+userDataBeans.getMonth()+"月"+userDataBeans.getDay()+"日"%><br>
        種別:<%= userDataBeans.getType() %><br>
        電話番号:<%= userDataBeans.getTell() %><br>
        自己紹介:<%= userDataBeans.getComment() %><br>

        <%--
        名前:<%= hs.getAttribute("name")%><br>
        生年月日:<%= hs.getAttribute("year")+"年"+hs.getAttribute("month")+"月"+hs.getAttribute("day")+"日"%><br>
        種別:<%= hs.getAttribute("type")%><br>
        電話番号:<%= hs.getAttribute("tell")%><br>
        自己紹介:<%= hs.getAttribute("comment")%><br>
        --%>

        <%--課題7 セッションの利用が終了したタイミングでセッションを削除する --%>
        <% hs.invalidate(); %>
        以上の内容で登録しました。<br>
        <%--課題1 JumsHelperクラスのhome()メソッドを用いてトップへのリンクを追加 --%>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
