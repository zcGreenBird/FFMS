package com.ffms.controller;

import com.ffms.dao.UserDao;
import com.ffms.domain.User;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获得request传来的参数
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        //封装到User类
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        //查询用户是否存在
        User us = new User();
        User userFlag = new UserDao().login(username, password);
        //response反馈
        if (userFlag != null) {
            response.getWriter().println("User:" + username + "登录成功!");
        } else {
            response.getWriter().println("用户不存在或密码错误!");
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
