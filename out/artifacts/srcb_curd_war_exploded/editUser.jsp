<%@ page import="com.test.pojo.Users" %><%--
  Created by IntelliJ IDEA.
  User: xiaocong
  Date: 2022/8/31
  Time: 上午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //接受传递过来的要修改的那个用户信息
    Users user = (Users) request.getAttribute("user");
%>
<form action="submitEditUserServlet" method="post">
    id:<input type="text" name="id" value="<%=user.getId()%>"/>
    name:<input type="text" name="name" value="<%=user.getName()%>"/>
    age:<input type="text" name="age" value="<%=user.getAge()%>"/>
    <input type="submit" name="sub" value="提交" />
</form>

</body>
</html>
