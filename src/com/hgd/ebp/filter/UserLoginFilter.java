package com.hgd.ebp.filter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginFilter implements Filter{
	 private static final String LOGIN_PREFIX = "/user/",
             LOGIN_KEYWORD = "UserLogin";
	 public static final String ATTR_ADMINUSERID = "UserID";

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        String requestURI = req.getRequestURI();
        String uri = requestURI.substring(requestURI.indexOf(LOGIN_PREFIX));
        session.removeAttribute("msg");
        
        if (uri.indexOf(LOGIN_KEYWORD) == -1) {
            Integer userid = (Integer)session.getAttribute(ATTR_ADMINUSERID);   //从session中取认证
            if (userid == null) {
            	String loginUri = "/EBP/index.jsp";
            	session.setAttribute("msg", false);//更改状态
               ((HttpServletResponse)response).sendRedirect(loginUri);
                return;      
            }
        }
        session.setAttribute("msg", true);
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

}
