package com.psm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psm.common.Page;
import com.psm.dao.GoodsDao;
import com.psm.dao.imp.GoodsDaoImp;
import com.psm.model.Goods;
import com.psm.model.GoodsView;

public class GoodsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GoodsServlet() {
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
		if (action == null) {;
			// 1.实例化业务对象
			GoodsDao goodsDao = new GoodsDaoImp();
			// 2.调用方法
			List<GoodsView> list = goodsDao.list();
			int count = list.size();
			// 设置当前页
			String str = request.getParameter("pageIndex");
			int pageIndex = (str == null) ? (1) : (Integer.parseInt(str));
			// 创建分页对象
			Page pages = new Page(count, pageIndex);
			// 计算总页数
			pages.setTotalCount(count);
			// 调用方法
			List<GoodsView> goodsList = goodsDao.getPageInfo(pages);
			// 得到总页数
			pages.getTotalPageCount();
			request.setAttribute("pages", pages);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("../goods/goodsList.jsp")
					.forward(request, response);
		} else if (action.equals("add")) {
			// 获得请求参数
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			float price = Float.parseFloat(request.getParameter("price"));
			float saleprice = Float.parseFloat(request.getParameter("saleprice"));
			float vipprice = Float.parseFloat(request.getParameter("vipprice"));
			String spec = request.getParameter("spec");
			String sid = request.getParameter("sid");
			float num = Float.parseFloat(request.getParameter("num"));
			String model = request.getParameter("model");
			String size = request.getParameter("size");
			// 封装成对象
			Goods good = new Goods(id, name, spec, model, size, sid, price, saleprice, vipprice, num);
			// 实例化业务对象
			GoodsDao goodBiz = new GoodsDaoImp();
			// 调用方法
			int count = goodBiz.add(good);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Goods.do");

			}
		} else if (action.equals("details")) {
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			GoodsDao goodBiz = new GoodsDaoImp();
			// 2.调用方法
			Goods good = goodBiz.findById(id);
			// 3.将对象保存在请求作用域
			request.setAttribute("good", good);
			// 4.转发到视图
			request.getRequestDispatcher("../goods/goodsView.jsp")
					.forward(request, response);
		} else if (action.equals("update")) {
			// 获得请求 参数
			String id = request.getParameter("id");
			// 1.实例化业务对象
			GoodsDao goodsDao = new GoodsDaoImp();
			// 2.调用方法
			Goods good = goodsDao.findById(id);
			List<String> sidList = goodsDao.findSid();
			
			// 3.将对象保存在请求作用域
			request.setAttribute("sidList", sidList);
			request.setAttribute("good", good);
			// 4.转发到视图
			request.getRequestDispatcher("../goods/goodsUpdate.jsp")
					.forward(request, response);
		} else if (action.equals("save")) {
			// 1.获得请求参数
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			float price = Float.parseFloat(request.getParameter("price"));
			float saleprice = Float.parseFloat(request.getParameter("saleprice"));
			float vipprice = Float.parseFloat(request.getParameter("vipprice"));
			String spec = request.getParameter("spec");
			String sid = request.getParameter("sid");
			float num = Float.parseFloat(request.getParameter("num"));
			String model = request.getParameter("model");
			String size = request.getParameter("size");
			// 封装成对象
			Goods good = new Goods(id, name, spec, model, size, sid, price, saleprice, vipprice, num);
			// 2实例化业务对象
			GoodsDao goodsDao = new GoodsDaoImp();
			// 3.调用方法
			goodsDao.update(good);
			// 4重定向
			response.sendRedirect("Goods.do");

		} else if (action.equals("del")) {
			// 获得请求参数
			String id = request.getParameter("id");
			// 获得Cookie
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id"))
						id = cookie.getValue();
				}
			}
			// 1.实例化业务对象
			GoodsDao goodBiz = new GoodsDaoImp();
			// 2.调用方法
			int count = goodBiz.del(id);
			if (count > 0) {
				// 4.一定要重定向，否则会陷入死循环，因为action参数还保留着所以还是会走这里或者直接指定一个jsp
				response.sendRedirect("Goods.do");
			}
		} else if (action.equals("addView")) {
			// 1.实例化业务对象
			GoodsDao goodsDao = new GoodsDaoImp();
			// 2.调用方法
			List<String> sidList = goodsDao.findSid();
			// 3.将对象保存在请求作用域
			request.setAttribute("sidList", sidList);
			// 4.转发到视图
			request.getRequestDispatcher("../goods/goodsAdd.jsp").forward(
					request, response);
		}else if(action.equals("search")){
			String name=request.getParameter("name");
			// 1.实例化业务对象
			GoodsDao biz = new GoodsDaoImp();
			// 2.调用方法
			List<GoodsView> list = biz.findByName(name);
			// 3.将数据存储在请求作用域
			request.setAttribute("goodsList", list);

			request.getRequestDispatcher("../goods/goodsList.jsp")
					.forward(request, response);
			
			
		}
	}
}
