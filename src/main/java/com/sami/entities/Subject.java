package com.sami.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String subjectName;
	@Column(unique =true)
	private String subjectCode;
	private String subjectCraditHour;
	private String preRequisite;
	private String semester;
	private Date date;
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(int id, String subjectName, String subjectCode, String subjectCraditHour,String preRequisite,String semester,Date date) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.subjectCraditHour = subjectCraditHour;
		this.preRequisite=preRequisite;
		this.semester=semester;
		this.date=date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectCraditHour() {
		return subjectCraditHour;
	}
	public void setSubjectCraditHour(String subjectCraditHour) {
		this.subjectCraditHour = subjectCraditHour;
	}
	
	public String getPreRequisite() {
		return preRequisite;
	}
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + ", subjectCode=" + subjectCode
				+ ", subjectCraditHour=" + subjectCraditHour + ", preRequisite=" + preRequisite + "]";
	}
	
}

