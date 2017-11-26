<%--
  Created by IntelliJ IDEA.
  User: yyc
  Date: 2017/10/26
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示用户，测试用</title>
</head>
<body>
${user.userName}
<FORM action="/sessions" method="post">
    <input name="_method" value="delete"/><br>
    <input type="submit" value="提交"/><br>
</FORM>
</body>
</html>
