package org.kitchenDet.servlet.user;

import com.mysql.cj.util.StringUtils;
import org.kitchenDet.pojo.User;
import org.kitchenDet.service.user.UserService;
import org.kitchenDet.service.user.UserServiceImpl;
import org.kitchenDet.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPwd = req.getParameter("newPassword");
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        UserService us = new UserServiceImpl();
        if(o!=null && !StringUtils.isNullOrEmpty(newPwd)){
            User nowUser = (User) o;
            boolean r = us.modifyPwd(nowUser.getID(),newPwd);
            if(r){
                req.setAttribute(Constants.MESSAGE,"密码修改成功！请重新登录");
                req.getSession().removeAttribute(Constants.USER_SESSION);
                req.getRequestDispatcher("/login.html").forward(req,resp);
            }else{
                req.setAttribute(Constants.MESSAGE,"密码修改失败！请重试");
                req.getRequestDispatcher("pwdmodify.html").forward(req,resp);
            }
        }else{
            req.setAttribute(Constants.MESSAGE,"新密码格式错误！请重试");
            req.getRequestDispatcher("pwdmodify.html").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
