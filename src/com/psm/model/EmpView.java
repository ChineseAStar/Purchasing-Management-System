package com.psm.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class EmpView {
	private String id;
	private String position;
	private String name;
	private String gender;
	private Date birth;
	private String phone;
	private int year;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getYear()
	{
		SimpleDateFormat sformat = new SimpleDateFormat();
		sformat.applyPattern("yyyy");
		year = Integer.parseInt(sformat.format(birth));
		return year;
	}
}
