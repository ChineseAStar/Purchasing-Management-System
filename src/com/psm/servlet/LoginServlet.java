package com.psm.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.psm.dao.imp.EmpsDaoImp;
import com.psm.model.*;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String passwd = request.getParameter("password");
		EmpsDaoImp dbAccess = new EmpsDaoImp();
		Emp emp = dbAccess.login(name,passwd);
		if(emp!=null){
			session.setAttribute("emp",emp);
			response.sendRedirect("emp/success.jsp");
		}else{
			session.setAttribute("message","登录信息有误，请重新登录！！！");
			response.sendRedirect("login.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
