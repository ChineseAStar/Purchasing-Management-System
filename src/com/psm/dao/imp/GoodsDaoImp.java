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
import com.psm.model.Gclass;
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
	public int add(String id,String name,String sid) {
		int count = 0;
		String sql = "insert into goods(id,name,sid) values(?,?,?)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, name);
			stmt.setString(3, sid);
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
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		
		return list;
	}

	@Override
	public List<Gclass> clist() {
		List<Gclass> clist = new ArrayList<Gclass>();
		String sql = "select daid,zhongid,xiaoid,name,type from class order by daid,zhongid,xiaoid";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Gclass clas = new Gclass();
				clas.setDaid(rs.getString(1));
				clas.setZhongid(rs.getString(2));
				clas.setXiaoid(rs.getString(3));
				clas.setName(rs.getString(4));
				clas.setType(Integer.parseInt(rs.getString(5)));
				clist.add(clas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return clist;
	}

	@Override
	public int addClass(String daid, String zhongid, String xiaoid, String name, int type) {
		int count = 0;
		String sql = "insert into class(daid,zhongid,xiaoid,name,type) values(?,?,?,?,?)";
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, daid);
			stmt.setString(2, zhongid);
			stmt.setString(3, xiaoid);
			stmt.setString(4, name);
			stmt.setInt(5, type);
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int delClass(String daid, String zhongid, String xiaoid) {
		int count = 0;
		conn = ConnectionFactory.getConnection();
		String sql = "";
		if(zhongid != "")
		{
			if(xiaoid != "") {
				sql = "delete from class where daid=? and zhongid=? and xiaoid=?";
			}else {
				sql = "delete from class where daid=? and zhongid=? and xiaoid is null";
			}
		}else {
			sql = "delete from class where daid=? and zhongid is null and xiaoid is null";
		}
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, daid);
			if(zhongid != "")
			{
				stmt.setString(2, zhongid);
				if(xiaoid != "") {
					stmt.setString(3, xiaoid);
				}
			}
			count = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public int findClass(String daid, String zhongid, String xiaoid) {
		int count = 1;
		conn = ConnectionFactory.getConnection();
		String sql = "";
		if(zhongid != "")
		{
			if(xiaoid != "") {
				sql = "select * from class where daid=? and zhongid=? and xiaoid=?";
			}else {
				sql = "select * from class where daid=? and zhongid=? and xiaoid is null";
			}
		}else {
			sql = "select * from class where daid=? and zhongid is null and xiaoid is null";
		}
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, daid);
			if(zhongid != "")
			{
				stmt.setString(2, zhongid);
				if(xiaoid != "") {
					stmt.setString(3, xiaoid);
				}
			}
			rs = stmt.executeQuery();
			while(rs.next())
			{
				count = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}

	@Override
	public String findgid(String gid1) {
		String sql = "select id from goods where id like ? order by id";
		conn = ConnectionFactory.getConnection();
		String gid2 = "001";
		String gid3 = gid1+gid2;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, gid1+"%");
			rs = stmt.executeQuery();
			while(rs.next() && gid3.equals(rs.getString(1)))
			{
				gid2 = Integer.parseInt("1"+gid2)+1+"";
				gid2 = gid2.substring(1, gid2.length());
				gid3 = gid1+gid2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return gid3;
	}
	public int checkdel(String daid, String zhongid, String xiaoid) {
		int count = 0;
		String sql = "";
		String content = "";
		if(xiaoid!="") {
			sql = "select * from goods where id like ?";
			content = daid+zhongid+xiaoid+"%";
		}else if(zhongid != ""){
			sql = "select * from class where daid=? and zhongid=? and xiaoid is not null";
			content = daid;
		}else {
			sql = "select * from class where daid=? and zhongid is not null";
			content = daid;
		}
		conn = ConnectionFactory.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			if(zhongid != "" && xiaoid == "") stmt.setString(2, zhongid);
			rs = stmt.executeQuery();
			while(rs.next()) count = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose.close(rs, stmt, conn);
		}
		return count;
	}
}


