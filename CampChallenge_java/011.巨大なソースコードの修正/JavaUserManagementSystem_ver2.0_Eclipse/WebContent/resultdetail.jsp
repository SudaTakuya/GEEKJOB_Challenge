<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	//変更点 セッションに保存した詳細データを利用するように変更
    UserDataDTO udd = (UserDataDTO)session.getAttribute("detailData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報詳細画面</title>
    </head>
    <body>
        <h1>詳細情報</h1>
        <h3>以下のユーザー情報の変更・削除を行うことができます</h3>
        名前:<%= udd.getName()%><br>
        生年月日:<%= udd.getBirthday()%><br>
        種別:<%= jh.exTypenum(udd.getType()) %><br>
        電話番号:<%= udd.getTell()%><br>
        自己紹介:<%= udd.getComment()%><br>
        登録日時:<%= udd.getNewDate()%><br><br>

        <%--変更点 不正アクセス防止コードを非表示で送信 --%>
        <form action="Update" method="POST">
        <input type="hidden" name="ac" value=<%= session.getAttribute("ac") %>>
        <input type="submit" name="update" value="変更"style="width:100px">
        </form><br>
        <form action="Delete" method="POST">
        <input type="hidden" name="ac" value=<%= session.getAttribute("ac") %>>
        <input type="submit" name="delete" value="削除"style="width:100px">
        </form>

		<%--変更点 遷移先から戻ったと判断できるコードを付与して検索結果へと戻るように変更 --%>
        <br><a href="/JavaUserManagementSystem_ver2.0_Eclipse/SearchResult?ac=<%= session.getAttribute("ac")%>&return=yes">検索結果へ戻る</a>
    </body>
</html>
