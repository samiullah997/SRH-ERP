package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DateSheetHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String dateSheetDate;
	private String department;
	private String dateSheetType;
	private String firstShiftTime;
	private String secondShiftTime;
	public DateSheetHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DateSheetHeader(int id, String dateSheetDate, String department, String dateSheetType, String firstShiftTime,
			String secondShiftTime) {
		super();
		this.id = id;
		this.dateSheetDate = dateSheetDate;
		this.department = department;
		this.dateSheetType = dateSheetType;
		this.firstShiftTime = firstShiftTime;
		this.secondShiftTime = secondShiftTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateSheetDate() {
		return dateSheetDate;
	}
	public void setDateSheetDate(String dateSheetDate) {
		this.dateSheetDate = dateSheetDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDateSheetType() {
		return dateSheetType;
	}
	public void setDateSheetType(String dateSheetType) {
		this.dateSheetType = dateSheetType;
	}
	public String getFirstShiftTime() {
		return firstShiftTime;
	}
	public void setFirstShiftTime(String firstShiftTime) {
		this.firstShiftTime = firstShiftTime;
	}
	public String getSecondShiftTime() {
		return secondShiftTime;
	}
	public void setSecondShiftTime(String secondShiftTime) {
		this.secondShiftTime = secondShiftTime;
	}
	@Override
	public String toString() {
		return "DateSheetHeader [id=" + id + ", dateSheetDate=" + dateSheetDate + ", department=" + department
				+ ", dateSheetType=" + dateSheetType + ", firstShiftTime=" + firstShiftTime + ", secondShiftTime="
				+ secondShiftTime + "]";
	}
	
}
