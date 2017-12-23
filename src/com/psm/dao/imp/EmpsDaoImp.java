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
import com.psm.dao.EmpsDao;
import com.psm.model.Emp;
import com.psm.model.EmpView;

public class EmpsDaoImp implements EmpsDao{

	private Connection conn = null;
	private PreparedStatement stm = null;
	private ResultSet rs = null;
	
	//登录方法
	public Emp login(String id,String passwd) {
	 Emp emp = null;
	 try{
			conn = ConnectionFactory.getConnection();
			String sql = "select name,position,gender from emp where id=? and passwd=?";
			stm = conn.prepareStatement(sql);
			stm.setString(1,id);
			stm.setString(2,passwd);
			//4.执行sql
			rs = stm.executeQuery();
			//5.处理结果集
			if(rs.next()){
				emp = new Emp();
				emp.setName(rs.getString(1));
				emp.setPosition(rs.getString(2));
				emp.setGender(rs.getString(3));
				emp.setId(id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6.关闭资源
			ResourceClose.close(rs,stm,conn);
		}
		return emp;		
	}

	@Override
	public int modifypwd(Emp emp, String newPwd) {
		String sql = "update emp set passwd=? where  id=? and passwd=?";
		conn = ConnectionFactory.getConnection();
		String[] param = {newPwd, emp.getId(),emp.getPasswd() };
		int count = 0;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, param[0]);
			stm.setString(2, param[1]);
			stm.setString(3, param[2]);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}
		return count;
	}

	@Override
	public int del(String id) {
		String sql = "delete from emp where id=?";
		conn = ConnectionFactory.getConnection();
		int count = 0;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, id);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}
		return count;
	}

	@Override
	public int add(Emp emp) {
		String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
		conn = ConnectionFactory.getConnection();
		int count = 0;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, emp.getId());
			stm.setString(2, emp.getPasswd());
			stm.setString(3, emp.getPosition());
			stm.setString(4, emp.getName());
			stm.setString(5, emp.getGender());
			stm.setDate(6, emp.getBirth());
			stm.setString(7, emp.getAddr());
			stm.setString(8, emp.getPhone());
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}
		return count;
	}

	@Override
	public int update(Emp emp) {
		String sql = "update emp set position=?,name=?,gender=?,birth=?,addr=?,phone=? where id=?";
		conn = ConnectionFactory.getConnection();
		int count = 0;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, emp.getPosition());
			stm.setString(2, emp.getName());
			stm.setString(3, emp.getGender());
			stm.setDate(4, emp.getBirth());
			stm.setString(5, emp.getAddr());
			stm.setString(6, emp.getPhone());
			stm.setString(7, emp.getId());
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}

		return count;
	}
	
	
	@Override
	public List<EmpView> list() {
		List<EmpView> lists = new ArrayList<EmpView>();
		String sql = "select id,name,gender,birth,phone,position from emp order by id";
		conn = ConnectionFactory.getConnection();

		// 2)ps对象
		try {
			stm = conn.prepareStatement(sql);
			// 3)rs对象
			rs = stm.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				EmpView empview = new EmpView();
				empview.setId(rs.getString(1));
				empview.setName(rs.getString(2));
				empview.setGender(rs.getString(3));
				empview.setBirth(rs.getDate(4));
				empview.setPhone(rs.getString(5));
				empview.setPosition(rs.getString(6));
				// 放在集合中
				lists.add(empview);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}

		return lists;
	}

	@Override
	public List<EmpView> getPageInfo(Page pages) {
		List<EmpView> lists = new ArrayList<EmpView>();
		int min = (pages.getCurrPageNo() - 1) * pages.getPageSize();
		int max = pages.getPageSize();
		String sql = " select top "+max
				+" id,name,gender,birth,phone,position from emp where id not in (select top "+min
				+" id from emp order by id)";
		conn = ConnectionFactory.getConnection();
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				EmpView empview = new EmpView();
				empview.setId(rs.getString(1));
				empview.setName(rs.getString(2));
				empview.setGender(rs.getString(3));
				empview.setBirth(rs.getDate(4));
				empview.setPhone(rs.getString(5));
				empview.setPosition(rs.getString(6));
				lists.add(empview);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}

		return lists;
	}


	@Override
	public Emp findById(String id) {
		Emp emp = new Emp();
	    String sql="select id,name,gender,birth,phone,addr,position from emp where id=?";
		conn = ConnectionFactory.getConnection();
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, id);
			rs = stm.executeQuery();
			if(rs.next()) {
				emp.setId(rs.getString(1));
				emp.setName(rs.getString(2));
				emp.setGender(rs.getString(3));
				emp.setBirth(rs.getDate(4));
				emp.setPhone(rs.getString(5));
				emp.setAddr(rs.getString(6));
				emp.setPosition(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}

		return emp;
	}

	@Override
	public List<EmpView> findByName(String name) {
		List<EmpView> lists = new ArrayList<EmpView>();
	    String sql="select id,name,gender,birth,phone,position from emp where name like ?";
		// 1)获得连接
		conn = ConnectionFactory.getConnection();
		// 2)ps对象
		try {
			stm = conn.prepareStatement(sql);
			//设置参数
			stm.setString(1,"%"+name+"%");
			// 3)rs对象
			rs = stm.executeQuery();
			// 遍历数据
			while(rs.next()) {
				// 4)获得数据
				EmpView empview = new EmpView();
				empview.setId(rs.getString(1));
				empview.setName(rs.getString(2));
				empview.setGender(rs.getString(3));
				empview.setBirth(rs.getDate(4));
				empview.setPhone(rs.getString(5));
				empview.setPosition(rs.getString(6));
				System.out.println(empview.getName());
				lists.add(empview);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stm, conn);
		}

		return lists;
	}
}
