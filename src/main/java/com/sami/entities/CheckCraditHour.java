package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CheckCraditHour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rollNo;
	private String semester;
	private String batch;
	private float semesterCraditHour;
	public CheckCraditHour() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckCraditHour(int id, String rollNo, String semester, String batch, float semesterCraditHour) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.semester = semester;
		this.batch = batch;
		this.semesterCraditHour = semesterCraditHour;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public float getSemesterCraditHour() {
		return semesterCraditHour;
	}
	public void setSemesterCraditHour(float semesterCraditHour) {
		this.semesterCraditHour = semesterCraditHour;
	}
	
	
	
}
