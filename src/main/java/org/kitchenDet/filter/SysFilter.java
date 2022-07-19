package org.kitchenDet.filter;

import org.kitchenDet.util.Constants;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getSession().getAttribute(Constants.USER_SESSION) == null){
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }else{
            chain.doFilter(request, response);
        }
    }
}
