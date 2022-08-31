package com.test;

import com.test.util.DbHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
@Author jiujiu
@Date 2022/8/31 - 上午10:37
*/
@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取你提交的要删除的id
        String id = req.getParameter("id");
        DbHelper dbHelper = new DbHelper();
        String sql = "delete from users where id = ?";
        ArrayList<Object> paramList = new ArrayList<>();
        paramList.add(id);
        int i = dbHelper.executeUpdate(sql, paramList);
        //跳转到显示页面
        if(i>0){
            resp.sendRedirect("showUserServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
