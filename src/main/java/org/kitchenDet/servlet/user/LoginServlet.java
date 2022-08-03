package org.kitchenDet.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.xdevapi.JsonArray;
import org.kitchenDet.pojo.User;
import org.kitchenDet.service.user.UserService;
import org.kitchenDet.service.user.UserServiceImpl;
import org.kitchenDet.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,String> result = new HashMap<>();
        if(loginUser!=null){
            System.out.println("登录成功");
            req.getSession().setAttribute(Constants.USER_SESSION,loginUser);
            result.put("status","true");
            result.put("url",req.getContextPath()+"/sys/showVideo.html");
        }else{
            System.out.println("登录失败");
            result.put("status","false");
            result.put("url","");
        }
        System.out.println("开始写入json");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(result));
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
