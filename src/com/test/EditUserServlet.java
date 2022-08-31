package com.test;

import com.test.pojo.Users;
import com.test.util.DbHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
@Author jiujiu
@Date 2022/8/31 - 上午9:49
*/
@WebServlet("/editUserServlet")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传递过来的id
        String id = req.getParameter("id");
        DbHelper dbHelper = new DbHelper();
        String sql =  "select * from users where id = ?";
        ArrayList<Object> paramList = new ArrayList<>();
        paramList.add(id);
        List<Map<String, Object>> mapList = dbHelper.executeQuery(sql, paramList);
        //因为根据id查，只有0或1两种可能
        if(mapList!=null){
            Map<String, Object> map = mapList.get(0);
            //组装成对象。
            Users user = new Users();
            user.setId(map.get("ID").toString());
            user.setName(map.get("NAME").toString());
            user.setAge(Integer.parseInt(map.get("AGE").toString()));
            req.setAttribute("user",user);
            System.out.println(user);
            req.getRequestDispatcher("editUser.jsp").forward(req,resp);
        }
    }
}
