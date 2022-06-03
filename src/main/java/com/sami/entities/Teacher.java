package com.sami.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teacherId;
	private String name;
	private String qualification;
	private Date date;
	private String address;
	private String contact;
	private String serialNo;
	
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Teacher(int teacherId, String name, String qualification, Date date, String address, String contact,
			String serialNo) {
		super();
		this.teacherId = teacherId;
		this.name = name;
		this.qualification = qualification;
		this.date = date;
		this.address = address;
		this.contact = contact;
		this.serialNo = serialNo;
	}


	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", name=" + name + ", qualification=" + qualification + ", date="
				+ date + ", address=" + address + ", contact=" + contact + ", serialNo=" + serialNo + "]";
	}
	
	

}
