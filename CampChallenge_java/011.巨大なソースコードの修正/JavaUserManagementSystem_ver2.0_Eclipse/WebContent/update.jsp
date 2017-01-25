<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.Calendar" %>

<%
    JumsHelper jh = JumsHelper.getInstance();
	//変更点 リクエストではなくセッションの値を利用して表示するように変更
	UserDataDTO udd = (UserDataDTO) session.getAttribute("detailData");
	Calendar cal = Calendar.getInstance();
	cal.setTime(udd.getBirthday());
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DAY_OF_MONTH);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS変更画面</title>
    </head>
    <body>
    <h1>変更</h1>
    <h3>変更を行いたい項目を修正してください</h3>
    <%--変更点 フォームの各項目を入力済みに変更 --%>
    <form action="UpdateResult" method="POST">
        名前:
        <input type="text" name="name" value=<%=udd.getName() %>>
        <br><br>

        生年月日:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%if(i == year) {%>
            	selected
            <% } %>><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <% for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <%if(i == month + 1) {%>
            	selected
            <% } %>><%= i %></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <% for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>" <%if(i == day) {%>
            	selected
            <% } %>><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>"
            	<%if(i == udd.getType()){%>
            		checked
            	<% } %> ><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        電話番号:
        <input type="text" name="tell" value=<%= udd.getTell() %>>
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%= udd.getComment() %></textarea><br><br>

		<%--変更点 不正アクセス防止コードを非表示で送信 --%>
        <input type="hidden" name="ac" value=<%= session.getAttribute("ac") %>>
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
    	<%--変更点 詳細画面へのリンクを追加 --%>
    	<br>
    	<a href="/JavaUserManagementSystem_ver2.0_Eclipse/resultdetail.jsp?ac=<%= session.getAttribute("ac")%>">詳細画面に戻る</a>
        <br>
        <%=jh.home()%>
    </body>
</html>
