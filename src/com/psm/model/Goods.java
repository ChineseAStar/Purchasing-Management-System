package com.psm.model;

import java.io.Serializable;

/**
 * 实体类，封装商品信息的JavaBean 
 * 实现序列化接口以便其实例可以安全的保存在HttpSession中
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class Goods implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private String id;//用户编号 
	private String name;// 产品名称
	private String spec;// 产品单位
	private String model;//产品型号
	private String size;//产品大小
	private String sid;//供应商编号
	private float price;// 产品进价
	private float saleprice;// 产品售价
	private float vipprice;// 产品vip价
	private float num;//库存

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 构造函数
	 */
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goods(String id, String name, String spec, String model, String size, String sid, float price,
			float saleprice, float vipprice, float num) {
		super();
		this.id = id;
		this.name = name;
		this.spec = spec;
		this.model = model;
		this.size = size;
		this.sid = sid;
		this.price = price;
		this.saleprice = saleprice;
		this.vipprice = vipprice;
		this.num = num;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}

	public float getVipprice() {
		return vipprice;
	}

	public void setVipprice(float vipprice) {
		this.vipprice = vipprice;
	}

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}


}