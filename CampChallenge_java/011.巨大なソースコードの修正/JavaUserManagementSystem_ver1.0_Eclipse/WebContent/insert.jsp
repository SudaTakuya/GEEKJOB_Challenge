<%@page import="javax.servlet.http.HttpSession" %>
<%--課題５ UserDataBeansに保存されたデータをつかうためにインポート分を追加 --%>
<%@page import="jums.JumsHelper,jums.UserDataBeans" %>
<%
    HttpSession hs = request.getSession();
	//課題５ insertconfilm.jspから戻ったか判断するためにセッションから"done"文字列を取得
	//       UserDataBeansのインスタンス生成を追加
	String done = (String)session.getAttribute("done");
	UserDataBeans userDataBeans = (UserDataBeans)hs.getAttribute("userDataBeans");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
    <form action="insertconfirm" method="POST">
    <%--課題５ insertconfirm.jspから戻った場合は各フォームに値が入力されたフォームを表示するように変更 --%>
    <%if(!done.equals("done")) {%>
	        名前:
	        <input type="text" name="name" value="">
	        <br><br>

	        生年月日:　
	        <select name="year">
	            <option value="">----</option>
	            <%
	            for(int i=1950; i<=2010; i++){ %>
	            <option value="<%=i%>"> <%=i%> </option>
	            <% } %>
	        </select>年
	        <select name="month">
	            <option value="">--</option>
	            <%
	            for(int i = 1; i<=12; i++){ %>
	            <option value="<%=i%>"><%=i%></option>
	            <% } %>
	        </select>月
	        <select name="day">
	            <option value="">--</option>
	            <%
	            for(int i = 1; i<=31; i++){ %>
	            <option value="<%=i%>"><%=i%></option>
	            <% } %>
	        </select>日
	        <br><br>

	        種別:
	        <br>
	        <input type="radio" name="type" value="1" checked>エンジニア<br>
	        <input type="radio" name="type" value="2">営業<br>
	        <input type="radio" name="type" value="3">その他<br>
	        <br>

	        電話番号:
	        <input type="text" name="tell" value="">
	        <br><br>

	        自己紹介文
	        <br>
	        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"></textarea><br><br>
	 <%} else { %>
			 名前:
	        <input type="text" name="name" value=<%= userDataBeans.getName() %>>
	        <br><br>

	        生年月日:　
	        <select name="year">
	            <option value="">----</option>
	            <%
	            for(int i=1950; i<=2010; i++){ %>
	            	<% if(i == Integer.parseInt(userDataBeans.getYear())) {%>
						<option value="<%=i %>" selected> <%=i%> </option>
	            	<% }else{ %>
	            		<option value="<%=i%>"> <%=i%> </option>
	            	<% } %>
	            <% } %>

	        </select>年
	        <select name="month">
	            <option value="">--</option>
	            <%
	            for(int i = 1; i<=12; i++){ %>
	            	<% if(i == Integer.parseInt(userDataBeans.getMonth())){ %>
						<option value="<%=i %>" selected> <%=i%> </option>
	            	<% }else{ %>
	            		<option value="<%=i%>"><%=i%></option>
	            	<% } %>
	            <% } %>
	        </select>月
	        <select name="day">
	            <option value="">--</option>
	            <%
	            for(int i = 1; i<=31; i++){ %>
	            	<% if(i == Integer.parseInt(userDataBeans.getDay())){ %>
						<option value="<%=i %>" selected> <%=i%> </option>
	            	<% }else{ %>
	            		<option value="<%=i%>"><%=i%></option>
	            	<% } %>
	            <% } %>
	        </select>日
	        <br><br>

	        種別:
	        <br>
	        <% switch((Integer.parseInt(userDataBeans.getType()))) {

	  			case 1 : %>
					<input type="radio" name="type" value="1" checked>エンジニア<br>
	        		<input type="radio" name="type" value="2">営業<br>
	        		<input type="radio" name="type" value="3">その他<br>
	  				<% break;
	  			case 2 : %>
					<input type="radio" name="type" value="1" >エンジニア<br>
	       			<input type="radio" name="type" value="2" checked>営業<br>
	        		<input type="radio" name="type" value="3">その他<br>
	  				<% break;
	  			case 3 : %>
					<input type="radio" name="type" value="1" >エンジニア<br>
	      		    <input type="radio" name="type" value="2">営業<br>
	                <input type="radio" name="type" value="3" checked>その他<br>
	  				<% break;
	        } %>
	        <br>

	        電話番号:
	        <input type="text" name="tell" value="<%= userDataBeans.getTell()%>">
	        <br><br>

	        自己紹介文
	        <br>
	        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%= userDataBeans.getComment() %></textarea><br><br>
	 <%} %>
	        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
	        <input type="submit" name="btnSubmit" value="確認画面へ">
	    </form>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
