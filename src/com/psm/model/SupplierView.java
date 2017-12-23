package com.psm.model;

public class SupplierView {

	private String id;
	private String name;
	private String agent;
	private String phone;
	private String postcode;
	public SupplierView() {
		super();
	}
	public SupplierView(String id, String name, String agent, String phone, String postcode) {
		super();
		this.id = id;
		this.name = name;
		this.agent = agent;
		this.phone = phone;
		this.postcode = postcode;
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
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
