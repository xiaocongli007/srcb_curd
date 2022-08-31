package com.test;

import com.test.pojo.Users;
import com.test.util.DbHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
@Author jiujiu
@Date 2022/8/31 - 上午9:14
*/

@WebServlet("/showUserServlet")
public class ShowUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbHelper dbHelper = new DbHelper();
        String sql = "select * from users";
        List<Map<String, Object>> mapList = dbHelper.executeQuery(sql);

        //将返回的用户列表组装成对象
        List<Users> usersList=  new ArrayList<>();

        //遍历所有查询的记录，每一次遍历都可以组成一个对象。
        for(Map<String,Object> map:mapList){
            Users user = new Users();
            String id = map.get("ID").toString();
            user.setId(id);
            String name = map.get("NAME").toString();
            user.setName(name);
            int age = Integer.parseInt(map.get("AGE").toString());
            user.setAge(age);
            usersList.add(user);
        }
        //转发到jsp页面，输出
        //将要转发的数据存入到请求对象
        System.out.println(usersList);
        req.setAttribute("usersList",usersList);
        //将请求对象转发到showUsers.jsp
        req.getRequestDispatcher("show.jsp").forward(req,resp);

    }
}
