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
import com.psm.dao.SuppliersDao;
import com.psm.model.Supplier;
import com.psm.model.SupplierMini;
import com.psm.model.SupplierView;

public class SuppliersDaoImp implements SuppliersDao {
	// 声明对象
	private ResultSet rs;
	private PreparedStatement stmt;
	private Connection conn;
	
	@Override
	public int list() {
		int rtn = 0;
		String sql = "select id from supplier order by id";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			// 3)rs对象
			rs = stmt.executeQuery();
			// 遍历数据
			while (rs.next()) rtn++;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return rtn;
	}

	@Override
	public Supplier findById(String id) {
		Supplier supplier = new Supplier();
	    String sql="select id,name,agent,phone,postcode,addr,bank,account from supplier where id=?";
		conn = ConnectionFactory.getConnection();
		// 2)ps对象
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				// 4)获得数据
				supplier.setId(rs.getString(1));
				supplier.setName(rs.getString(2));
				supplier.setAgent(rs.getString(3));
				supplier.setPhone(rs.getString(4));
				supplier.setPostcode(rs.getString(5));
				supplier.setAddr(rs.getString(6));
				supplier.setBank(rs.getString(7));
				supplier.setAccount(rs.getString(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}

		return supplier;
	}

	@Override
	public List<SupplierView> getPageInfo(Page pages) {
		List<SupplierView> lists = new ArrayList<SupplierView>();
		int min = (pages.getCurrPageNo() - 1) * pages.getPageSize();
		int max = pages.getPageSize();
		String sql = " select top "+
		max+" id,name,agent,phone,postcode from supplier where id not in (select top "+
		min+" id from supplier order by id)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				SupplierView supplier = new SupplierView();
				supplier.setId(rs.getString(1));
				supplier.setName(rs.getString(2));
				supplier.setAgent(rs.getString(3));
				supplier.setPhone(rs.getString(4));
				supplier.setPostcode(rs.getString(5));
				lists.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return lists;
	}

	@Override
	public int update(Supplier supplier) {
		String sql = "update supplier set name=?,agent=?,phone=?,addr=?,postcode=?,bank=?,account=? "
				+ "where id=?";
		int count = 0;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, supplier.getName());
			stmt.setString(2, supplier.getAgent());
			stmt.setString(3, supplier.getPhone());
			stmt.setString(4, supplier.getAddr());
			stmt.setString(5, supplier.getPostcode());
			stmt.setString(6, supplier.getBank());
			stmt.setString(7, supplier.getAccount());
			stmt.setString(8, supplier.getId());
			count = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int del(String id) {
		// 1)sql语句
		String sql = "delete from supplier where id=?";
		int count = 0;
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int add(Supplier supplier) {
		// 1)sql语句
		String sql = "insert into supplier values(?,?,?,?,?,?,?,?)";
		conn = ConnectionFactory.getConnection();
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, supplier.getId());
			stmt.setString(2, supplier.getName());
			stmt.setString(3, supplier.getAgent());
			stmt.setString(4, supplier.getPhone());
			stmt.setString(5, supplier.getAddr());
			stmt.setString(6, supplier.getPostcode());
			stmt.setString(7, supplier.getBank());
			stmt.setString(8, supplier.getAccount());
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public List<SupplierView> findByName(String name) {
		List<SupplierView> lists = new ArrayList<SupplierView>();
		String sql = "select id,name,agent,phone,postcode from supplier where name like ? order by id";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+name+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				SupplierView supplier = new SupplierView();
				supplier.setId(rs.getString(1));
				supplier.setName(rs.getString(2));
				supplier.setAgent(rs.getString(3));
				supplier.setPhone(rs.getString(4));
				supplier.setPostcode(rs.getString(5));
				lists.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return lists;
	}
	
	@Override
	public List<SupplierMini> findSid() {
		String sql = "select id,name from supplier order by id";
		conn = ConnectionFactory.getConnection();
		List<SupplierMini> list = new ArrayList<SupplierMini>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				SupplierMini supplier = new SupplierMini();
				supplier.setId(rs.getString(1));
				supplier.setName(rs.getString(2));
				list.add(supplier);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		
		return list;
	}
}
