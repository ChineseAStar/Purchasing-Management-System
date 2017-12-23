package com.psm.model;

public class GoodsView {
	
	
	private String id;// 用户编号
	private String name;// 产品名称
	private String spec;// 产品单位
	private String sid;
	private float num;
	private float price;// 产品进价
	private float saleprice;// 产品售价
	private float vipprice;// 产品vip价

	public GoodsView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsView(String id, String name, String spec, float price, float saleprice, float vipprice) {
		super();
		this.id = id;
		this.name = name;
		this.spec = spec;
		this.price = price;
		this.saleprice = saleprice;
		this.vipprice = vipprice;
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

}
