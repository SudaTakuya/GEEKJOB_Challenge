<%@page
        import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報検索画面</title>
    </head>
    <h1>検索</h1>
    <h3>検索の条件を入力してください</h3>
    <body>
    	<%--変更点 searchの機能用件に従い、値の受け渡しをGETで行うように変更 --%>
         <form action="SearchResult" method="GET">
        名前:
        <input type="text" name="name">
        <br><br>

        生年:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>"><%=i%></option>
            <% } %>
        </select>年生まれ
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>"><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        <%--変更点 不正アクセス防止コードを非表示で送信 --%>
        <input type="hidden" name="ac" value=<%= session.getAttribute("ac") %>>
        <%--変更点 遷移先のページから戻ってきたか判断するコードを非表示で送信 --%>
        <input type="hidden" name="return" value="no">
        <input type="submit" name="btnSubmit" value="検索">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
