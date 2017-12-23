package com.psm.dao;

import java.util.List;

import com.psm.common.Page;
import com.psm.model.Supplier;
import com.psm.model.SupplierMini;
import com.psm.model.SupplierView;

public interface SuppliersDao {
	int list();//查询所有用户
	Supplier findById(String id);//根据id查找用户
	List<SupplierView> findByName(String name);//根据名称查找
	List<SupplierView> getPageInfo(Page pages);
	int update(Supplier suppliers);//更新用户信息
	int del(String id);//删除用户
	int add(Supplier suppliers);//添加
	List<SupplierMini> findSid();
}
