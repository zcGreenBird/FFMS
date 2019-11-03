package com.ffms.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ProjectName: FFMS-parent
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 17975
 * @Description: ${description}
 * @Date: 2019/10/29 10:52
 * @Version: 1.0
 */
@WebServlet(name = "LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("");
            return;
        }
        session.removeAttribute("user");
        response.sendRedirect("");

    }
}
