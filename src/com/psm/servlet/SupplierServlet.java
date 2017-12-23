package com.psm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psm.common.Page;
import com.psm.dao.SuppliersDao;
import com.psm.dao.imp.SuppliersDaoImp;
import com.psm.model.Supplier;
import com.psm.model.SupplierView;

public class SupplierServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SupplierServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// 获取动作参数，判断要执行的动作
		String action = request.getParameter("action");
		// ///////////////////执行动作/////////////////////////////
		if (action == null) {
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			int count = suppliersDao.list();
			String str = request.getParameter("pageIndex");
			int pageIndex = (str == null) ? (1) : (Integer.parseInt(str));
			// 6.创建分页对象
			Page pages = new Page(count, pageIndex);
			// 计算总页数
			pages.setTotalCount(count);
			// 7.调用方法
			List<SupplierView> supplierList = suppliersDao.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			// 8.保存数据在请求域
			request.setAttribute("supplierList", supplierList);
			request.setAttribute("pages", pages);

			request.getRequestDispatcher("../supplier/supplierList.jsp")
					.forward(request, response);
		} else if (action.equals("add")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String agent = request.getParameter("agent");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("address");
			String postcode = request.getParameter("postcode");
			String bank = request.getParameter("bank");
			String account = request.getParameter("account");
			Supplier supplier = new Supplier(id, name, agent, phone, addr, postcode, bank, account);
			SuppliersDao supplierDao = new SuppliersDaoImp();
			int count = supplierDao.add(supplier);
			if (count > 0) {
				response.sendRedirect("Suppliers.do");
			}
		} else if (action.equals("details")) {
			String id = request.getParameter("id");
			System.out.println(id);
			// 1.实例化业务对象
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			// 2.调用方法
			Supplier supplier = suppliersDao.findById(id);
			// 3.将对象保存在请求作用域
			request.setAttribute("supplier", supplier);
			System.out.println(supplier.getName());
			// 4.转发到视图
			request.getRequestDispatcher("../supplier/supplierView.jsp")
					.forward(request, response);

		} else if (action.equals("update")) {
			String id = request.getParameter("id");
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			// 2.调用方法
			Supplier supplier = suppliersDao.findById(id);
			// 3.将对象保存在请求作用域
			request.setAttribute("supplier", supplier);
			// 4.转发到视图
			request.getRequestDispatcher("../supplier/supplierUpdate.jsp")
					.forward(request, response);

		} else if (action.equals("save")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String agent = request.getParameter("agent");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("address");
			String postcode = request.getParameter("postcode");
			String bank = request.getParameter("bank");
			String account = request.getParameter("account");
			Supplier supplier = new Supplier(id, name, agent, phone, addr, postcode, bank, account);
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			int count = suppliersDao.update(supplier);
			if (count > 0) {
				response.sendRedirect("Suppliers.do");
			}

		} else if (action.equals("del")) {
			String id = request.getParameter("id");
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id"))
						id = cookie.getValue();
				}
			}
			SuppliersDao suppliersDao= new SuppliersDaoImp();
			// 2.调用方法
			int count = suppliersDao.del(id);
			if (count > 0) {
				response.sendRedirect("Suppliers.do");
			}
		}else if(action.equals("search")){
			
			String name=request.getParameter("name");
			System.out.println("search------------->"+name);
			// 1.实例化业务对象
			SuppliersDao suppliersdao = new SuppliersDaoImp();
			// 2.调用方法
			List<SupplierView> list = suppliersdao.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("supplierList", list);

			request.getRequestDispatcher("../supplier/supplierList.jsp")
					.forward(request, response);
		}
	}
}