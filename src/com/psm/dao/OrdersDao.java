package com.psm.dao;

import java.sql.Timestamp;
import java.util.List;

import com.psm.common.Page;
import com.psm.model.GoodsItem;
import com.psm.model.GoodsMini;
import com.psm.model.GoodsView;
import com.psm.model.Order;
import com.psm.model.OrderDetail;
import com.psm.model.OrderView;

public interface OrdersDao {
	int list();//查询所有商品信息
	OrderDetail findById(String id);//根据id查找商品
	Order findByIdForUpdate(String id);//根据id查找商品
	List<OrderView> getPageInfo(Page pages);
	int update(String id,Timestamp submittime);//更新商品信息
	int del(String id);//删除商品信息
	int add(Order order);//添加商品信息
	int check(OrderDetail order);
	int addod(String oid,String gid);
	int delodg(String oid,String gid);
	int delodallg(String oid);
	int updatenum(String oid,String gid,float num);
	List<GoodsMini> findGforS(String sid);
	List<GoodsItem> findGforO(String oid);
	List<OrderView> search(String sname,String checkresult);//根据商品名称，供应商和是否支付搜索相应的订单
	String findoid(String oid);
	List<GoodsView> findForAdd(String sname,String choice);//根据名称查找
}
