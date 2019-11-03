package com.ffms.controller;

import com.ffms.dao.UserDao;
import com.ffms.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: FFMS-parent
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 17975
 * @Description: ${description}
 * @Date: 2019/10/30 9:52
 * @Version: 1.0
 */
@WebServlet(name = "UpDateServlet")
public class UpDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDao dao = new UserDao();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("uerName");
        String password = request.getParameter("password");
        try {
            Boolean success = dao.updateUser(id,name,password);
            if (success) {//信息更新成功

            }else{//信息更新失败

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
