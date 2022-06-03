package com.sami.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentFeeRcord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rollNo;
	private String studentName;
	private String registrationNo;
	private String fatherName;
	private String semester;
	private String dapartment;
	private String batch;
	private String feeType;
	@Column(length = 100)
	private int totalFee;
	@Column(length = 100)
	private int submittedFee;
	private String feeSumbmittedDate;
	private float craditHour;
	public StudentFeeRcord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentFeeRcord(int id, String rollNo, String studentName, String registrationNo, String fatherName,
			String semester, String dapartment, String batch, String feeType, int totalFee, int submittedFee,
			String feeSumbmittedDate, float craditHour) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.registrationNo = registrationNo;
		this.fatherName = fatherName;
		this.semester = semester;
		this.dapartment = dapartment;
		this.batch = batch;
		this.feeType = feeType;
		this.totalFee = totalFee;
		this.submittedFee = submittedFee;
		this.feeSumbmittedDate = feeSumbmittedDate;
		this.craditHour = craditHour;
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getDapartment() {
		return dapartment;
	}
	public void setDapartment(String dapartment) {
		this.dapartment = dapartment;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	public int getSubmittedFee() {
		return submittedFee;
	}
	public void setSubmittedFee(int submittedFee) {
		this.submittedFee = submittedFee;
	}
	public String getFeeSumbmittedDate() {
		return feeSumbmittedDate;
	}
	public void setFeeSumbmittedDate(String feeSumbmittedDate) {
		this.feeSumbmittedDate = feeSumbmittedDate;
	}
	public float getCraditHour() {
		return craditHour;
	}
	public void setCraditHour(float craditHour) {
		this.craditHour = craditHour;
	}
	@Override
	public String toString() {
		return "StudentFeeRcord [id=" + id + ", rollNo=" + rollNo + ", studentName=" + studentName + ", registrationNo="
				+ registrationNo + ", fatherName=" + fatherName + ", semester=" + semester + ", dapartment="
				+ dapartment + ", batch=" + batch + ", feeType=" + feeType + ", totalFee=" + totalFee
				+ ", submittedFee=" + submittedFee + ", feeSumbmittedDate=" + feeSumbmittedDate + ", craditHour="
				+ craditHour + "]";
	}
	
	

}
