<%@ page import="com.test.pojo.Users" import="java.util.List"  %><%--
  Created by IntelliJ IDEA.
  User: xiaocong
  Date: 2022/8/31
  Time: 上午9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<a href="addUser.jsp"> 添加一个新用户</a>
<table>
<%
        //从转发过来的请求对象中，获取用户列表
        List<Users> usersList = (List<Users>) request.getAttribute("usersList");
        for(Users user:usersList){

%>
        <tr>
            <td>id:<%=user.getId()%></td>
            <td>Name:<%=user.getName()%></td>
            <td>Age:<%=user.getAge()%></td>
            <td><a href="editUserServlet?id=<%=user.getId()%>"> 修改</a></td>
            <td><a href="deleteUserServlet?id=<%=user.getId()%>"> 删除</a></td>
        </tr>

<%

        }

%>
</table>
</body>

</html>
