package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DutyRosterRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstTeacherNameOne;
	private String firstTeacherNameTwo;
	private String firstTeacherNameThree;
	private String secondTeacherNameOne;
	private String secondTeacherNameTwo;
	private String secondTeacherNameThree;
	private String date;
	public DutyRosterRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DutyRosterRecord(int id, String firstTeacherNameOne, String firstTeacherNameTwo, String firstTeacherNameThree,
			String secondTeacherNameOne, String secondTeacherNameTwo, String secondTeacherNameThree, String date) {
		super();
		this.id = id;
		this.firstTeacherNameOne = firstTeacherNameOne;
		this.firstTeacherNameTwo = firstTeacherNameTwo;
		this.firstTeacherNameThree = firstTeacherNameThree;
		this.secondTeacherNameOne = secondTeacherNameOne;
		this.secondTeacherNameTwo = secondTeacherNameTwo;
		this.secondTeacherNameThree = secondTeacherNameThree;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstTeacherNameOne() {
		return firstTeacherNameOne;
	}
	public void setFirstTeacherNameOne(String firstTeacherNameOne) {
		this.firstTeacherNameOne = firstTeacherNameOne;
	}
	public String getFirstTeacherNameTwo() {
		return firstTeacherNameTwo;
	}
	public void setFirstTeacherNameTwo(String firstTeacherNameTwo) {
		this.firstTeacherNameTwo = firstTeacherNameTwo;
	}
	public String getFirstTeacherNameThree() {
		return firstTeacherNameThree;
	}
	public void setFirstTeacherNameThree(String firstTeacherNameThree) {
		this.firstTeacherNameThree = firstTeacherNameThree;
	}
	public String getSecondTeacherNameOne() {
		return secondTeacherNameOne;
	}
	public void setSecondTeacherNameOne(String secondTeacherNameOne) {
		this.secondTeacherNameOne = secondTeacherNameOne;
	}
	public String getSecondTeacherNameTwo() {
		return secondTeacherNameTwo;
	}
	public void setSecondTeacherNameTwo(String secondTeacherNameTwo) {
		this.secondTeacherNameTwo = secondTeacherNameTwo;
	}
	public String getSecondTeacherNameThree() {
		return secondTeacherNameThree;
	}
	public void setSecondTeacherNameThree(String secondTeacherNameThree) {
		this.secondTeacherNameThree = secondTeacherNameThree;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "DutyRosterRecord [id=" + id + ", firstTeacherNameOne=" + firstTeacherNameOne + ", firstTeacherNameTwo="
				+ firstTeacherNameTwo + ", firstTeacherNameThree=" + firstTeacherNameThree + ", secondTeacherNameOne="
				+ secondTeacherNameOne + ", secondTeacherNameTwo=" + secondTeacherNameTwo + ", secondTeacherNameThree="
				+ secondTeacherNameThree + ", date=" + date + "]";
	}

}
