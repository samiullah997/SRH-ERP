package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ResultList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String batch;
	private String date;
	private String paperStatus;
	private String rollNo;
	private String studentName;
	private String registrationNo;
	private String fatherName;
	private String semester;
	private String department;
	private String paperName;
	private int marks;
	private float value;
	private float gp;
	private String status;
	private int paperGrad;
	private int totalMarks;
	
	
	
	public ResultList() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ResultList(int id, String batch, String date, String paperStatus, String rollNo, String studentName,
			String registrationNo, String fatherName, String semester, String department, String paperName, int marks,
			float value, float gp, String status, int paperGrad, int totalMarks) {
		super();
		this.id = id;
		this.batch = batch;
		this.date = date;
		this.paperStatus = paperStatus;
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.registrationNo = registrationNo;
		this.fatherName = fatherName;
		this.semester = semester;
		this.department = department;
		this.paperName = paperName;
		this.marks = marks;
		this.value = value;
		this.gp = gp;
		this.status = status;
		this.paperGrad = paperGrad;
		this.totalMarks = totalMarks;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getBatch() {
		return batch;
	}



	public void setBatch(String batch) {
		this.batch = batch;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getPaperStatus() {
		return paperStatus;
	}



	public void setPaperStatus(String paperStatus) {
		this.paperStatus = paperStatus;
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



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getPaperName() {
		return paperName;
	}



	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}



	public int getMarks() {
		return marks;
	}



	public void setMarks(int marks) {
		this.marks = marks;
	}



	public float getValue() {
		return value;
	}



	public void setValue(float value) {
		this.value = value;
	}



	public float getGp() {
		return gp;
	}



	public void setGp(float gp) {
		this.gp = gp;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getPaperGrad() {
		return paperGrad;
	}



	public void setPaperGrad(int paperGrad) {
		this.paperGrad = paperGrad;
	}



	public int getTotalMarks() {
		return totalMarks;
	}



	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}



	@Override
	public String toString() {
		return "ResultList [id=" + id + ", batch=" + batch + ", date=" + date + ", paperStatus=" + paperStatus
				+ ", rollNo=" + rollNo + ", studentName=" + studentName + ", registrationNo=" + registrationNo
				+ ", fatherName=" + fatherName + ", semester=" + semester + ", department=" + department
				+ ", paperName=" + paperName + ", marks=" + marks + ", value=" + value + ", gp=" + gp + ", status="
				+ status + ", paperGrad=" + paperGrad + ", totalMarks=" + totalMarks + "]";
	}



	
}
