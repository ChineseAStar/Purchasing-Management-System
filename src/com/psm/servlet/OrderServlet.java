package com.psm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.psm.common.Page;
import com.psm.dao.GoodsDao;
import com.psm.dao.OrdersDao;
import com.psm.dao.SuppliersDao;
import com.psm.dao.imp.GoodsDaoImp;
import com.psm.dao.imp.OrdersDaoImp;
import com.psm.dao.imp.SuppliersDaoImp;
import com.psm.model.Goods;
import com.psm.model.GoodsItem;
import com.psm.model.GoodsMini;
import com.psm.model.GoodsView;
import com.psm.model.Order;
import com.psm.model.OrderDetail;
import com.psm.model.OrderView;
import com.psm.model.Supplier;
import com.psm.model.SupplierMini;

public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public OrderServlet() {
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
		String action = request.getParameter("action");
		// ///////////////////执行动作/////////////////////////////
		if (action == null) {
			OrdersDao ordersDao = new OrdersDaoImp();
			int count = ordersDao.list();
			String str = request.getParameter("pageIndex");
			int pageIndex = (str == null) ? (1) : (Integer.parseInt(str));
			Page pages = new Page(count, pageIndex);
			pages.setTotalCount(count);
			List<OrderView> ordersList = ordersDao.getPageInfo(pages);
			pages.getTotalPageCount();
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			List<SupplierMini> suppliersList = suppliersDao.findSid();
			request.setAttribute("suppliersList", suppliersList);

			request.setAttribute("ordersList", ordersList);
			request.setAttribute("pages", pages);
			request.getRequestDispatcher("../order/ordersList.jsp").forward(
					request, response);

		} else if (action.equals("gooddetails")) {
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
		} else if (action.equals("addView")) {
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			List<SupplierMini> supplierminiList = suppliersDao.findSid();
			List<GoodsView> goodsList = new ArrayList<GoodsView>();
			request.setAttribute("suppliersminiList", supplierminiList);
			if(request.getAttribute("goodsList") == null)
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("../order/orderAdd.jsp").forward(
					request, response);

		} else if (action.equals("add")) {
			OrdersDao ordersDao = new OrdersDaoImp();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp ordertime = null;
			try {
				ordertime = new Timestamp(format.parse(request.getParameter("ordertime")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String eid = request.getParameter("eid");
			String sid = request.getParameter("sid");
			String oid = ""+(ordertime.getMonth()+1)+ordertime.getDate()+sid;
			oid = ordersDao.findoid(oid);
			Order order = new Order();
			order.setEid(eid);
			order.setSid(sid);
			order.setId(oid);
			order.setOrdertime(ordertime);
			int count = ordersDao.add(order);
			if (count > 0) {
				response.sendRedirect("Orders.do?action=update&id="+oid);
			}
		} else if (action.equals("autoadd")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp ordertime = null;
			try {
				ordertime = new Timestamp(format.parse(request.getParameter("ordertime")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String eid = request.getParameter("eid");
			OrdersDao ordersDao = new OrdersDaoImp();
			HttpSession session = request.getSession();
			List<GoodsView> goodsList = (List<GoodsView>)session.getAttribute("goodsList");
			List<String> sidList = new ArrayList<String>();
			Map hm = new HashMap();
			for(GoodsView tmp:goodsList) {
				List<GoodsView> tmpsList = null;
				if(hm.get(tmp.getSid()) == null)
				{
					sidList.add(tmp.getSid());
					tmpsList = new ArrayList<GoodsView>();
					hm.put(tmp.getSid(), tmpsList);
				}
				tmpsList = (ArrayList<GoodsView>)hm.get(tmp.getSid());
				tmpsList.add(tmp);
			}
			for(String sid:sidList)
			{
				String oid = ""+(ordertime.getMonth()+1)+ordertime.getDate()+sid;
				oid = ordersDao.findoid(oid);
				Order order = new Order();
				order.setEid(eid);
				order.setSid(sid);
				order.setId(oid);
				order.setOrdertime(ordertime);
				int count = ordersDao.add(order);
				if (count > 0) {
					List<GoodsView> addList = (List<GoodsView>) hm.get(sid);
					for(GoodsView good:addList) {
						String gid = good.getId();
						ordersDao.addod(oid, gid);
						ordersDao.updatenum(oid, gid, good.getNum()*10);
						ordersDao.update(oid,ordertime);
					}
				}
				String id = request.getParameter("id");
				ordersDao.update(id,ordertime);
				
			}
			response.sendRedirect("Orders.do?");
		} else if (action.equals("details")) {
			String id = request.getParameter("id");
			OrdersDao ordersDao = new OrdersDaoImp();
			List<GoodsItem> goodsList = ordersDao.findGforO(id);
			OrderDetail order = ordersDao.findById(id);
			request.setAttribute("order", order);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("../order/orderView.jsp").forward(
					request, response);

		} else if (action.equals("update")) {
			String id = request.getParameter("id");
			OrdersDao ordersDao = new OrdersDaoImp();
			OrderDetail order = ordersDao.findById(id);

			List<GoodsMini> goodsminiList =	ordersDao.findGforS(order.getSid());
			List<GoodsItem> goodsList =	ordersDao.findGforO(order.getId());
			
			request.setAttribute("order", order);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("goodsminiList", goodsminiList);
			request.getRequestDispatcher("../order/orderUpdate.jsp").forward(
					request, response);
		} else if (action.equals("save")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp submittime = null;
			try {
				submittime = new Timestamp(format.parse(request.getParameter("submittime")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String id = request.getParameter("id");
			OrdersDao ordersDao = new OrdersDaoImp();
			ordersDao.update(id,submittime);
			// 重定向
			response.sendRedirect("Orders.do");

		} else if (action.equals("del")) {
			String id = request.getParameter("id");
			Cookie[] cookies = request.getCookies();
			if (cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("oid"))
						id = cookie.getValue();
				}
			}
			OrdersDao ordersDao = new OrdersDaoImp();
			ordersDao.delodallg(id);
			int count = ordersDao.del(id);
			if (count > 0) {
				response.sendRedirect("Orders.do");
			}
//		} else if (action.equals("supplierajax")) {
//			request.setCharacterEncoding("utf-8");
//
//			OrdersDao ordersDao = new OrdersDaoImp();
//
//			String gname = request.getParameter("gname");
//			List<Supplier> list = ordersDao.findSupByGname(gname);
//			// 将list封装成json数组
//			JSONArray jsonArray = JSONArray.fromObject(list);
//			// 处理中文乱码
//			response.setHeader("content-type", "text/html;charset=UTF-8");
//			PrintWriter printWriter = response.getWriter();
//			printWriter.println(jsonArray);
//
//		} else if (action.equals("goodajax")) {
//			request.setCharacterEncoding("utf-8");
//
//			OrdersDao biz = new OrdersDaoImp();
//
//			String gname = request.getParameter("gname");
//			String sid = request.getParameter("sid");
//			// 根据商品名称和供应商sid找到gid
//			int gid = biz.findGid(gname, sid);
//			GoodsDao goodBiz = new GoodsDaoImp();
//			// 根据id找到商品信息
//			Goods good = goodBiz.findById(gid);
//			// 将list封装成json数组
//			JSONArray jsonArray = JSONArray.fromObject(good);
//			// 处理中文乱码
//			response.setHeader("content-type", "text/html;charset=UTF-8");
//			PrintWriter printWriter = response.getWriter();
//			printWriter.println(jsonArray);

		} else if (action.equals("search")) {
			String sname = request.getParameter("sname");
			String opaid = request.getParameter("opaid");
			// 2实例化业务对象
			OrdersDao ordersDao = new OrdersDaoImp();
			// 3调用方法
			List<OrderView> ordersList=ordersDao.search(sname, opaid);
			//4保存数据到请求作用域
			SuppliersDao suppliersDao = new SuppliersDaoImp();
			List<SupplierMini> suppliersList = suppliersDao.findSid();
			request.setAttribute("suppliersList", suppliersList);
			
			request.setAttribute("ordersList", ordersList);
			// 5.转发到视图
			request.getRequestDispatcher("../order/ordersList.jsp").forward(
					request, response);

		} else if (action.equals("addgoods")) {
			String gid = request.getParameter("gid");
			String oid = request.getParameter("oid");
			OrdersDao ordersDao = new OrdersDaoImp();
			int count = ordersDao.addod(oid, gid);
			if(count <= 0) {
				request.setAttribute("errmsg", "添加失败，该商品已存在！");
			}
			request.getRequestDispatcher("Orders.do?action=update&id="+oid).forward(request,response);
		} else if (action.equals("delgoods")) {
			String gid = request.getParameter("gid");
			String oid = request.getParameter("oid");
			OrdersDao ordersDao = new OrdersDaoImp();
			int count = ordersDao.delodg(oid, gid);
			if(count > 0)
				response.sendRedirect("Orders.do?action=update&id="+oid);
		} else if (action.equals("updatenum")) {
			Float num = Float.parseFloat(request.getParameter("num"));
			String gid = request.getParameter("gid");
			String oid = request.getParameter("oid");
			OrdersDao ordersDao = new OrdersDaoImp();
			int count = ordersDao.updatenum(oid, gid, num);
			if(count > 0)
				response.sendRedirect("Orders.do?action=update&id="+oid);
		} else if (action.equals("checkView")) {
			String id = request.getParameter("id");
			OrdersDao ordersDao = new OrdersDaoImp();
			List<GoodsItem> goodsList = ordersDao.findGforO(id);
			OrderDetail order = ordersDao.findById(id);
			request.setAttribute("order", order);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("../order/orderCheck.jsp").forward(
					request, response);
		} else if (action.equals("check")) {
			String id = request.getParameter("id");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp checktime = null;
			try {
				checktime = new Timestamp(format.parse(request.getParameter("checktime")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			String checksuggest = request.getParameter("checksuggest");
			String checkresult = request.getParameter("checkresult");
			OrderDetail order = new OrderDetail();
			order.setId(id);
			order.setChecktime(checktime);
			order.setCid(cid);
			order.setCname(cname);
			order.setChecksuggest(checksuggest);
			order.setCheckresult(checkresult);
			OrdersDao ordersDao = new OrdersDaoImp();
			int count = ordersDao.check(order);
			if(count > 0) {
				response.sendRedirect("Orders.do");
			}
		} else if (action.equals("shaixuan")) {
			String sname = request.getParameter("sname");
			String choice = request.getParameter("choice");
			OrdersDao ordersDao = new OrdersDaoImp();
			List<GoodsView> goodsList = ordersDao.findForAdd(sname, choice);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("Orders.do?action=addView").forward(
					request, response);
			
		} else if (action.equals("delgfora")) {
			String id = request.getParameter("id");
			HttpSession session = request.getSession();
			List<GoodsView> goodsList = (List<GoodsView>)session.getAttribute("goodsList");
			for(GoodsView good : goodsList) {
				if(good.getId().equals(id)) {
					goodsList.remove(good);
					break;
				}
			}
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("Orders.do?action=addView").forward(
					request, response);
			
		}
	}
}