package com.psm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RightFilter implements Filter {

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String head = uri.substring(lastIndex + 1);
		HttpSession session = httpServletRequest.getSession();
		String position = (String) session.getAttribute("position");
		if("Users.do".equals(head)) {
			if("系统管理员".equals(position)){
				chain.doFilter(request, response);
			}else if("经理".equals(position) || "采购员".equals(position)){
				httpServletResponse.sendRedirect("emp/rightlimit.jsp");
			}else {
				httpServletResponse.sendRedirect("login.jsp");
			}
		}else if("Suppliers.do".equals(head)) {
			if("经理".equals(position)){
				httpServletResponse.sendRedirect("emp/rightlimit.jsp");
			}else if("系统管理员".equals(position) || "采购员".equals(position)){
				chain.doFilter(request, response);
			}else {
				httpServletResponse.sendRedirect("login.jsp");
			}
		}else if("Orders.do".equals(head)) {
			String action = request.getParameter("action");
			if("经理".equals(position)){
				if("addView".equals(action) || "update".equals(action) || "del".equals(action)) {
					httpServletResponse.sendRedirect("emp/rightlimit.jsp");
				}else {
					chain.doFilter(request, response);
				}
			}else if("采购员".equals(position)) {
				if("checkView".equals(action)) {
					httpServletResponse.sendRedirect("emp/rightlimit.jsp");
				}else {
					chain.doFilter(request, response);
				}
			}else if("系统管理员".equals(position)) {
				chain.doFilter(request, response);
			}else {
				httpServletResponse.sendRedirect("login.jsp");
			}
		}else if("Goods.do".equals(head)) {
			if("经理".equals(position)){
				httpServletResponse.sendRedirect("emp/rightlimit.jsp");
			}else if("系统管理员".equals(position) || "采购员".equals(position)){
				chain.doFilter(request, response);
			}else {
				httpServletResponse.sendRedirect("login.jsp");
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
