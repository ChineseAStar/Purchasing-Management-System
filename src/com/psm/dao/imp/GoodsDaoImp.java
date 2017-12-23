package com.psm.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.psm.common.ConnectionFactory;
import com.psm.common.Page;
import com.psm.common.ResourceClose;
import com.psm.dao.GoodsDao;
import com.psm.model.Goods;
import com.psm.model.GoodsMini;
import com.psm.model.GoodsView;

public class GoodsDaoImp implements GoodsDao{
	// 声明对象
	private ResultSet rs;
	private PreparedStatement stmt;
	private Connection conn;
	
	
	@Override
	public List<GoodsView> list() {
		List<GoodsView> lists = new ArrayList<GoodsView>();
		String sql = "select id,name,price,saleprice,vipprice,spec,sid,num from goods";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				GoodsView good = new GoodsView();
				good.setId(rs.getString(1));
				good.setName(rs.getString(2));
				good.setPrice(rs.getFloat(3));
				good.setSaleprice(rs.getFloat(4));
				good.setVipprice(rs.getFloat(5));
				good.setSpec(rs.getString(6));
				good.setSid(rs.getString(7));
				good.setNum(rs.getFloat(8));
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
	public Goods findById(String id) {
		Goods good = new Goods();
	    String sql="select * from goods where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				good.setId(rs.getString(1));
				good.setName(rs.getString(2));
				good.setSpec(rs.getString(3));
				good.setModel(rs.getString(4));
				good.setSize(rs.getString(5));
				good.setSid(rs.getString(6));
				good.setPrice(rs.getFloat(7));
				good.setSaleprice(rs.getFloat(8));
				good.setVipprice(rs.getFloat(9));
				good.setNum(rs.getFloat(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}

		return good;
	}

	@Override
	public List<GoodsView> getPageInfo(Page pages) {
		List<GoodsView> lists = new ArrayList<GoodsView>();
		int min = (pages.getCurrPageNo() - 1) * pages.getPageSize();
		int max = pages.getPageSize();
		String sql = " select top "+max
				+" id,name,price,saleprice,vipprice,spec,num,sid from goods where id not in (select top "+min
				+" id from goods order by id)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				GoodsView good = new GoodsView();
				good.setId(rs.getString(1));
				good.setName(rs.getString(2));
				good.setPrice(rs.getFloat(3));
				good.setSaleprice(rs.getFloat(4));
				good.setVipprice(rs.getFloat(5));
				good.setSpec(rs.getString(6));
				good.setNum(rs.getFloat(7));
				good.setSid(rs.getString(8));
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
	public int update(Goods good) {
		int count = 0;
		String sql = "update goods set name=?,price=?,saleprice=?,vipprice=?,spec=?,model=?,size=?,sid=?,num=? where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, good.getName());
			stmt.setFloat(2, good.getPrice());
			stmt.setFloat(3, good.getSaleprice());
			stmt.setFloat(4, good.getVipprice());
			stmt.setString(5, good.getSpec());
			stmt.setString(6, good.getModel());
			stmt.setString(7, good.getSize());
			stmt.setString(8, good.getSid());
			stmt.setFloat(9, good.getNum());
			stmt.setString(10, good.getId());
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int del(String id) {
		int count = 0;
		String sql = "delete from goods where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int add(Goods good) {
		int count = 0;
		String sql = "insert into goods values(?,?,?,?,?,?,?,?,?,?)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, good.getId());
			stmt.setString(2, good.getName());
			stmt.setString(3, good.getSpec());
			stmt.setString(4, good.getModel());
			stmt.setString(5, good.getSize());
			stmt.setString(6, good.getSid());
			stmt.setFloat(7, good.getPrice());
			stmt.setFloat(8, good.getSaleprice());
			stmt.setFloat(9, good.getVipprice());
			stmt.setFloat(10, good.getNum());
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public List<String> findSid() {
		List<String> list = new ArrayList<String>();
		String sql = "select id from supplier order by id";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String tmp = rs.getString(1);
				list.add(tmp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}

		return list;
	}

	@Override
	public List<GoodsView> findByName(String name) {
		List<GoodsView> lists = new ArrayList<GoodsView>();
	    String sql="select id,name,price,saleprice,vipprice,spec,sid,num from goods where name like ?";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+name+"%");
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
	public List<GoodsMini> findGid() {
		String sql = "select id,name from goods order by id";
		conn = ConnectionFactory.getConnection();
		List<GoodsMini> list = new ArrayList<GoodsMini>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				GoodsMini goods = new GoodsMini();
				goods.setId(rs.getString(1));
				goods.setName(rs.getString(2));
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		
		return list;
	}
}


