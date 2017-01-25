<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.text.SimpleDateFormat" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	UserDataDTO udd = (UserDataDTO) request.getAttribute("updateData");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <h1>変更結果</h1><br>
        <%--変更点 変更結果の表示を追加 --%>
        名前:<%= udd.getName() %><br>
        生年月日:<%= sdf.format(udd.getBirthday()) %><br>
        種別: <%= jh.exTypenum(udd.getType()) %><br>
        電話番号:<%= udd.getTell() %><br>
        自己紹介:<%= udd.getComment() %><br><br>
        以上の内容で更新しました。<br><br>
    <%--変更点 更新処理を行っているため、詳細画面に戻る際に再度ID検索を実行する --%>
    <a href = "/JavaUserManagementSystem_ver2.0_Eclipse/ResultDetail?ac=<%= session.getAttribute("ac")%>&id=<%= udd.getUserID()%>">詳細画面へ戻る</a><br>
    <%=jh.home()%>
    </body>
</html>
