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
@Date 2022/8/31 - 上午10:08
*/
@WebServlet("/submitEditUserServlet")
public class SubmitEditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取修改后的用户信息
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        //提交给数据库修改
        DbHelper dbHelper = new DbHelper();
        String sql = "update users set name = ?,age=? where id = ?";
        ArrayList<Object> paramList = new ArrayList<>();
        paramList.add(name);
        paramList.add(age);
        paramList.add(id);
        int i = dbHelper.executeUpdate(sql, paramList);
        //修改完以后，跳转到显示页面
        resp.sendRedirect("showUserServlet");




    }
}
