package com.ffms.controller;

import com.ffms.dao.UserDao;
import com.ffms.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ProjectName: FFMS-parent
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 17975
 * @Description: ${description}
 * @Date: 2019/10/30 8:04
 * @Version: 1.0
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDao dao = new UserDao();
        String userName = request.getParameter("userName");
        int id = Integer.parseInt(request.getParameter("id"));
        String firstPassword = request.getParameter("firstPassword");
        String secondPassword = request.getParameter("secondPassword");
        try {
            if (firstPassword.equals(secondPassword)) {//判断两次密码是否一致
                User user = dao.selectUserByUserName(userName);//判断该用户名是否被使用
                if (user == null) {//该用户名没有被使用
                    boolean success = dao.insertUser(id, userName, firstPassword);//注册操作
                    if (success){//注册成功

                    }else{//注册失败

                    }
                } else {//该用户名已经被使用

                }
            } else {//两次密码不一致的处理方式

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
