package com.hgd.ebp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="PerformanceFilter", urlPatterns={"/*"})
public class PerformanceFilter implements Filter {
	private FilterConfig fConfig;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		long begTime = System.currentTimeMillis();   //1970.1.1 0:0
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis(); 
		
		StringBuffer msg = ((HttpServletRequest)request).getRequestURL();
		msg.insert(0, "���ܲ��ԣ� [");
		msg.append("] ��ʱ ");
		msg.append(endTime - begTime);
		msg.append("ms.");

		ServletContext application = fConfig.getServletContext();  //��ȡȫ��������
		application.log(msg.toString());
	}

	public void destroy() {}
}
