package com.psm.dao;

import java.util.List;

import com.psm.common.Page;
import com.psm.model.Emp;
import com.psm.model.EmpView;

public interface EmpsDao {
	Emp login(String name,String passwd);//用户登录
	int modifypwd(Emp user,String newPwd);//修改密码
	List<EmpView> list();//查询所有用户
	Emp findById(String id);//根据id查找用户
	List<EmpView> findByName(String name);//根据用户名查找用户
	List<EmpView> getPageInfo(Page pages);
	int update(Emp user);//更新用户信息
	int del(String id);//删除用户
	int add(Emp user);//添加
}
