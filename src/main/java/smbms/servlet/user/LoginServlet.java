package smbms.servlet.user;

import com.smbms.pojo.User;
import com.smbms.service.user.UserService;
import com.smbms.service.user.UserServiceImpl;
import com.smbms.util.Constants;

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
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        User loginUser = us.login(userCode,userPassword);
        if(loginUser!=null){
            req.getSession().setAttribute(Constants.USER_SESSION,loginUser);
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
