package com.psm.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psm.common.Page;
import com.psm.dao.EmpsDao;
import com.psm.dao.imp.EmpsDaoImp;
import com.psm.model.Emp;
import com.psm.model.EmpView;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 获取动作参数，判断要执行的动作
		String action = request.getParameter("action");
		// ///////////////////执行动作/////////////////////////////
		if (action == null) {
			// 实例化业务对象
			EmpsDao empsdao = new EmpsDaoImp();
			// 调用方法
			List<EmpView> list = empsdao.list();
			// 获得总个数
			int count = list.size();
			// 设置当前页
			String str = request.getParameter("pageIndex");
			int pageIndex = (str == null) ? (1) : (Integer.parseInt(str));
			// 创建分页对象
			Page pages = new Page(count, pageIndex);
			// 计算总页数
			pages.setTotalCount(count);
			// 调用方法
			List<EmpView> empsList = empsdao.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			// 保存数据在请求域
			request.setAttribute("empsList", empsList);
			request.setAttribute("pages", pages);
			request.getRequestDispatcher("../emp/empsList.jsp").forward(
					request, response);
		} else if (action.equals("details")) {
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			EmpsDao empsDao = new EmpsDaoImp();
			// 2.调用方法
			Emp emp = empsDao.findById(id);
			// 3.将对象保存在请求作用域
			request.setAttribute("emp", emp);
			// 4.转发到视图
			request.getRequestDispatcher("../emp/empView.jsp").forward(
					request, response);
		} else if (action.equals("add")) {
			// 获得请求参数
			String id = request.getParameter("userId");
			String name = request.getParameter("userName");
			String passwd = request.getParameter("userpassword");
			String gender = request.getParameter("sex");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = null;
			try {
				birth = new Date(format.parse(request.getParameter("houseDate")).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String phone = request.getParameter("userphone");
			String addr = request.getParameter("userAddress");
			String position = request.getParameter("userlei");
			// 封装成对象
			Emp emp = new Emp(id, passwd, position, name, gender, birth, addr, phone);
			// 实例化业务对象
			EmpsDao empsDao = new EmpsDaoImp();
			// 调用方法
			int count = empsDao.add(emp);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		} else if (action.equals("update")) {
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			EmpsDao empsDao = new EmpsDaoImp();
			// 2.调用方法
			Emp emp = empsDao.findById(id);
			// 3.将对象保存在请求作用域
			request.setAttribute("emp", emp);
			// 4.转发到视图
			request.getRequestDispatcher("../emp/empUpdate.jsp").forward(
					request, response);
		} else if (action.equals("save")) {
			// 获得请求 参数
			String id = request.getParameter("id");
			String name = request.getParameter("userName");
			String gender = request.getParameter("sex");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = null;
			try {
				birth = new Date(format.parse(request.getParameter("houseDate")).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String phone = request.getParameter("userphone");
			String addr = request.getParameter("userAddress");
			String position = request.getParameter("userlei");
			// 封装对象
			Emp emp = new Emp();
			emp.setId(id);
			emp.setName(name);
			emp.setGender(gender);
			emp.setBirth(birth);
			emp.setPhone(phone);
			emp.setAddr(addr);
			emp.setPosition(position);
			// 1.实例化业务对象
			EmpsDao empsdao = new EmpsDaoImp();
			// 2.调用方法
			int count = empsdao.update(emp);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		} else if (action.equals("del")) {
			// 获得请求参数
			String id = request.getParameter("delid");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("uid"))
						id = cookie.getValue();
				}
			}
			// 1.实例化业务对象
			EmpsDao empsDao = new EmpsDaoImp();
			// 2.调用方法
			int count = empsDao.del(id);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Users.do");
			}
		}else if(action.equals("search")){
			String name=request.getParameter("name");
			System.out.println("search------------->"+name);
			// 1.实例化业务对象
			EmpsDao empsDao = new EmpsDaoImp();
			// 2.调用方法
			List<EmpView> list = empsDao.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("empsList", list);

			request.getRequestDispatcher("../emp/empsList.jsp")
					.forward(request, response);
			
		}

	}

}
