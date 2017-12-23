package com.psm.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.psm.common.ConnectionFactory;
import com.psm.common.Page;
import com.psm.common.ResourceClose;
import com.psm.dao.OrdersDao;
import com.psm.model.GoodsItem;
import com.psm.model.GoodsMini;
import com.psm.model.GoodsView;
import com.psm.model.Order;
import com.psm.model.OrderDetail;
import com.psm.model.OrderView;

public class OrdersDaoImp implements OrdersDao {
	// 声明对象
	private ResultSet rs;
	private PreparedStatement stmt;
	private Connection conn;

	@Override
	public int list() {
		int rtn = 0;
		String sql = "select id from order_view";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		while(rs.next()) rtn++;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return rtn;
	}

	@Override
	public OrderDetail findById(String id) {
		OrderDetail order = new OrderDetail();
		String sql = "select id,ordertime,sid,sname,saddr,spostcode,cost,ename,submittime,checksuggest,checktime,checkresult,cname "
				+ "from order_view where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				order.setId(rs.getString(1));
				order.setOrdertime(rs.getTimestamp(2));
				order.setSid(rs.getString(3));
				order.setSname(rs.getString(4));
				order.setSaddr(rs.getString(5));
				order.setSpostcode(rs.getString(6));
				order.setCost(rs.getFloat(7));
				order.setEname(rs.getString(8));
				order.setSubmittime(rs.getTimestamp(9));
				order.setChecksuggest(rs.getString(10));
				order.setChecktime(rs.getTimestamp(11));
				order.setCheckresult(rs.getString(12));
				order.setCname(rs.getString(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return order;
	}

	@Override
	public List<OrderView> getPageInfo(Page pages) {
		List<OrderView> lists = new ArrayList<OrderView>();
		int min = (pages.getCurrPageNo() - 1) * pages.getPageSize();
		int max = pages.getPageSize();

		String sql = " select top "+max
				+" id,ordertime,sname,cost,checkresult "
				+ "from order_view where id not in(select top "+min
				+" id from order_view order by id)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				// 4)获得数据
				OrderView order = new OrderView();
				order.setId(rs.getString(1));
				order.setOrdertime(rs.getTimestamp(2));
				order.setSname(rs.getString(3));
				order.setCost(rs.getFloat(4));
				order.setCheckresult(rs.getString(5));
				lists.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return lists;
	}

	@Override
	public int update(String id,Timestamp submittime) {
		int count = 0;
		String sql = "update order_table set submittime=? where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(2,id);
			stmt.setTimestamp(1,submittime);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int del(String id) {
		int count = 0;
		String sql = "delete from order_table where id="+id;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}		
		return count;
	}

	@Override
	public int add(Order order) {
		String sql = "insert into order_table(id,ordertime,sid,eid) values(?,?,?,?)";
		int count = 0;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,order.getId());
			stmt.setTimestamp(2,order.getOrdertime());
			stmt.setString(3,order.getSid());
			stmt.setString(4, order.getEid());
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	

	@Override
	public List<OrderView> search( String sname, String checkresult) {
		List<OrderView> lists = new ArrayList<OrderView>();
		String sql = "select id,ordertime,sname,cost,checkresult "
				+ "from order_view where sname like ? and checkresult like ? ";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+ sname+"%");
			stmt.setString(2, "%"+ checkresult+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderView order = new OrderView();
				order.setId(rs.getString(1));
				order.setOrdertime(rs.getTimestamp(2));
				order.setSname(rs.getString(3));
				order.setCost(rs.getFloat(4));
				order.setCheckresult(rs.getString(5));
				lists.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			ResourceClose.close(rs, stmt, conn);
		}

		return lists;
	}

	@Override
	public Order findByIdForUpdate(String id) {
		Order order = new Order();
		String sql = "select id,ordertime,gid,num,eid,submittime,checksuggest,checktime,checkresult,sid "
				+ "from order_table where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				order.setId(rs.getString(1));
				order.setOrdertime(rs.getTimestamp(2));
				order.setGid(rs.getString(3));
				order.setNum(rs.getFloat(4));
				order.setSubmittime(rs.getTimestamp(5));
				order.setChecksuggest(rs.getString(6));
				order.setChecktime(rs.getTimestamp(7));
				order.setCheckresult(rs.getString(8));
				order.setSid(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return order;
	}
	
	public List<GoodsItem> findGforO(String oid){
		String sql = "select g.id,g.name,g.spec,g.model,o.num,g.price from goods g,order_detail o where g.id=o.gid and o.oid="+oid;
		conn = ConnectionFactory.getConnection();
		List<GoodsItem> lists = new ArrayList<GoodsItem>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				GoodsItem goods = new GoodsItem();
				goods.setId(rs.getString(1));
				goods.setName(rs.getString(2));
				goods.setSpec(rs.getString(3));
				goods.setModel(rs.getString(4));
				goods.setNum(rs.getFloat(5));
				goods.setPrice(rs.getFloat(6));
				lists.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
		
	}

	@Override
	public List<GoodsMini> findGforS(String sid) {
		String sql = "select id,name from goods where sid="+sid;
		conn = ConnectionFactory.getConnection();
		List<GoodsMini> lists = new ArrayList<GoodsMini>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				GoodsMini goods = new GoodsMini();
				goods.setId(rs.getString(1));
				goods.setName(rs.getString(2));
				lists.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public int addod(String oid, String gid) {
		String sql = "insert into order_detail(oid,gid) values(?,?);";
		int count = 0;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oid);
			stmt.setString(2, gid);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int updatenum(String oid, String gid, float num) {
		int count = 0;
		String sql = "update order_detail set num=? where oid=? and gid=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, num);
			stmt.setString(2, oid);
			stmt.setString(3, gid);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}	
		return count;		
	}

	@Override
	public int delodg(String oid, String gid) {
		int count = 0;
		String sql = "delete from order_detail where oid=? and gid=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oid);
			stmt.setString(2, gid);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;	
	}
	
	@Override
	public String findoid(String oid) {
		String sql = "select id from order_table where id like ? order by id";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oid+"%");
			rs = stmt.executeQuery();
			oid = oid+"01";
			while(rs.next() && oid.equals(rs.getString(1)))
			{
				oid = Integer.parseInt(oid)+1+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return oid;
	}

	@Override
	public int check(OrderDetail order) {
		String sql = "update order_table set cid=?,cname=?,checktime=?,checksuggest=?,checkresult=? where id=?";
		conn = ConnectionFactory.getConnection();
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getCid());
			stmt.setString(2, order.getCname());
			stmt.setTimestamp(3, order.getChecktime());
			stmt.setString(4, order.getChecksuggest());
			stmt.setString(5, order.getCheckresult());
			stmt.setString(6, order.getId());
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public List<GoodsView> findForAdd(String sname, String choice) {
		List<GoodsView> lists = new ArrayList<GoodsView>();
	    String sql="select id,name,price,saleprice,vipprice,spec,sid,num from goods "
	    		+ "where sid in (select id from supplier where name like ?) and num "+choice;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+sname+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				GoodsView good = new GoodsView();
				good.setId(rs.getString(1));
				good.setName(rs.getString(2));
				good.setPrice(rs.getFloat(3));
				good.setSaleprice(rs.getFloat(4));
				good.setVipprice(rs.getFloat(5));
				good.setSpec(rs.getString(6));
				good.setNum(rs.getFloat(8));
				good.setSid(rs.getString(7));
				lists.add(good);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return lists;
	}

	@Override
	public int delodallg(String oid) {
		int count = 0;
		String sql = "delete from order_detail where oid=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, oid);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}
	
}
