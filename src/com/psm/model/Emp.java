package com.psm.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Emp {

	private String id;
	private String passwd;
	private String position;
	private String name;
	private String gender;
	private Date birth;
	private String addr;
	private String phone;
	private int year;
	
	public Emp() {
		
	}
	
	public Emp(String id,String passwd) {
		this.id = id;
		this.passwd = passwd;
	}

	public String getId() {
		return id;
	}

	public Emp(String id, String passwd, String position, String name, String gender, Date birth, String addr,
			String phone) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.position = position;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.addr = addr;
		this.phone = phone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPerson() {
		String person = " "+name;
		if(gender.equals("男"))
		{
			person += " 先生";
		}else if(gender.equals("女"))
		{
			person += " 女士";
		}
		
		return person;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public int getYear()
	{
		SimpleDateFormat sformat = new SimpleDateFormat();
		sformat.applyPattern("yyyy");
		year = Integer.parseInt(sformat.format(birth));
		return year;
	}
	
}
