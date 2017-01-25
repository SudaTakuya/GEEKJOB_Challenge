<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	//変更点 リクエストではなくセッションのデータを利用して表示するように変更
    UserDataDTO udd = (UserDataDTO) session.getAttribute("detailData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>削除確認</h1>
    このレコードを本当に削除しますか？<br><br>
    名前:<%= udd.getName()%><br>
    生年月日:<%= udd.getBirthday()%><br>
    種別:<%= jh.exTypenum(udd.getType())%><br>
    電話番号:<%= udd.getTell()%><br>
    自己紹介:<%= udd.getComment()%><br>
    登録日時:<%= udd.getNewDate()%><br><br>

	<%--変更点 不正アクセス防止コードをリクエストパラメータに追加 --%>
    <form action="DeleteResult" method="POST">
      <input type="hidden" name="ac" value=<%= session.getAttribute("ac") %>>
      <input type="submit" name="YES" value="はい"style="width:100px">
    </form><br>
    <form action="/JavaUserManagementSystem_ver2.0_Eclipse/resultdetail.jsp?ac=<%= session.getAttribute("ac")%>" method="POST">
      <input type="submit" name="NO" value="詳細画面に戻る"style="width:100px">
    </form>
    <%--変更点 トップへのリンクを追加 --%>
    <br><a href="/JavaUserManagementSystem_ver2.0_Eclipse/">トップへ戻る</a>
    </body>
</html>
