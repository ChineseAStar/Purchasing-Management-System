package com.psm.dao;

import java.util.List;

import com.psm.common.Page;
import com.psm.model.Goods;
import com.psm.model.GoodsMini;
import com.psm.model.GoodsView;

public interface GoodsDao {
	List<GoodsView> list();//查询所有商品信息
	Goods findById(String id);//根据id查找商品
	List<GoodsView> findByName(String name);//根据名称查找
	List<GoodsView> getPageInfo(Page pages);
	int update(Goods good);//更新商品信息
	int del(String id);//删除商品信息
	int add(Goods good);//添加商品信息
	List<String> findSid();//查找供应商编号
	List<GoodsMini> findGid();
	
}
