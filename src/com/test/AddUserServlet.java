package com.test;

import com.test.util.DbHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
@Author jiujiu
@Date 2022/8/31 - 上午10:46
*/

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

        DbHelper dbHelper = new DbHelper();
        String sql = "insert into users values(?,?,?)";
        ArrayList<Object> list = new ArrayList<>();
        list.add(id);
        list.add(name);
        list.add(age);
        int i = dbHelper.executeUpdate(sql, list);
        if(i>0){
            resp.sendRedirect("showUserServlet");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
