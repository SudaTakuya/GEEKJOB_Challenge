
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除結果画面</title>
    </head>
    <body>
    <h1>削除確認</h1>
    削除しました。<br><br>

    <%-- 変更点 検索結果、トップ画面に戻るリンクを追加 --%>
    <a href="/JavaUserManagementSystem_ver2.0_Eclipse/SearchResult?ac=<%= session.getAttribute("ac")%>&return=yes">検索結果に戻る</a><br>
    <a href="/JavaUserManagementSystem_ver2.0_Eclipse/">トップへ戻る</a>
    </body>
</html>
