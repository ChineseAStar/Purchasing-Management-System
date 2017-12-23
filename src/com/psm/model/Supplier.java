package com.psm.model;
/**
 * 实体类，封装商品提供商信息的JavaBean 
 * 实现序列化接口以便其实例可以安全的保存在HttpSession中
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class Supplier {
	
	private String id;
	private String name;
	private String agent;
	private String phone;
	private String addr;
	private String postcode;
	private String bank;
	private String account;

	public Supplier() {
		super();
	}
	
	public Supplier(String id, String name, String agent, String phone, String addr, String postcode, String bank,
			String account) {
		super();
		this.id = id;
		this.name = name;
		this.agent = agent;
		this.phone = phone;
		this.addr = addr;
		this.postcode = postcode;
		this.bank = bank;
		this.account = account;
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

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
