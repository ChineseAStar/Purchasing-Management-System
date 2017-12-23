package com.psm.model;

import java.sql.Timestamp;

public class Order {
		
	private String id;
	private Timestamp ordertime;
	private String gid;
	private Float num;
	private String eid;
	private Timestamp submittime;
	private String checksuggest;
	private Timestamp checktime;
	private String checkresult;
	private String sid;

	public Order() {
		super();
	}

	public Order(String id, Timestamp ordertime, String gid, Float num, String eid, Timestamp submittime,
			String checksuggest, Timestamp checktime, String checkresult, String setid) {
		super();
		this.id = id;
		this.ordertime = ordertime;
		this.gid = gid;
		this.num = num;
		this.eid = eid;
		this.submittime = submittime;
		this.checksuggest = checksuggest;
		this.checktime = checktime;
		this.checkresult = checkresult;
		this.sid = setid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	
	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Timestamp getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Timestamp submittime) {
		this.submittime = submittime;
	}

	public String getChecksuggest() {
		return checksuggest;
	}

	public void setChecksuggest(String checksuggest) {
		this.checksuggest = checksuggest;
	}

	public Timestamp getChecktime() {
		return checktime;
	}

	public void setChecktime(Timestamp checktime) {
		this.checktime = checktime;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String setid) {
		this.sid = setid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
}
