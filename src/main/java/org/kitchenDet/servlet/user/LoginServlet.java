package org.kitchenDet.servlet.user;

import org.kitchenDet.pojo.User;
import org.kitchenDet.service.user.UserService;
import org.kitchenDet.service.user.UserServiceImpl;
import org.kitchenDet.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet:控制层，调用业务层代码
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService us = new UserServiceImpl();
        String userCode = req.getParameter("username");
        String userPassword = req.getParameter("password");
        User loginUser = us.login(userCode,userPassword);
        if(loginUser!=null){
            req.getSession().setAttribute(Constants.USER_SESSION,loginUser);
            resp.sendRedirect("sys/frame.html");
        }else{
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.html").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
