<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.ArrayList" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	//変更点 検索結果の全件を格納したArrayListを表示に使用するように変更
	ArrayList<UserDataDTO> uddlist = (ArrayList<UserDataDTO>) request.getAttribute("resultData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <h3>詳細を確認したいユーザーを選択してください</h3>
        <table border=1>
            <tr bgcolor="silver">
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>
            <%--変更点 ArrayListを用いて検索結果の全件を表示する処理に変更 --%>
            <% for(UserDataDTO udd : uddlist){%>
            	<tr>
            		<%--変更点 不正アクセス防止コードをリクエストパラメータに追加 --%>
	            	<td><a href="ResultDetail?id=<%= udd.getUserID()%>&ac=<%= session.getAttribute("ac") %>"><%= udd.getName()%></a></td>
	                <td><%= udd.getBirthday()%></td>
	                <td><%= jh.exTypenum(udd.getType())%></td>
	                <td><%= udd.getNewDate()%></td>
                </tr>
            <% } %>
        </table><br>
    </body>
    <%=jh.home()%>
</html>
