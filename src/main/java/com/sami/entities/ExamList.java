package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExamList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String batch;
	private String feeSumbmittedDate;
	private String paperStatus;
	private String rollNo;
	private String studentName;
	private String registrationNo;
	private String fatherName;
	private String semester;
	private String department;
	private String paperName;
	private String courseCode;
	private String status;
	private String exam;
	private String listDate;
	public ExamList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamList(int id, String batch, String feeSumbmittedDate, String paperStatus, String rollNo, String studentName,
			String registrationNo, String fatherName, String semester, String department, String paperName,
			String courseCode, String status,String exam,String listDate) {
		super();
		this.id = id;
		this.batch = batch;
		this.feeSumbmittedDate = feeSumbmittedDate;
		this.paperStatus = paperStatus;
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.registrationNo = registrationNo;
		this.fatherName = fatherName;
		this.semester = semester;
		this.department = department;
		this.paperName = paperName;
		this.courseCode = courseCode;
		this.status = status;
		this.exam=exam;
		this.listDate=listDate;
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
	public String getfeeSumbmittedDate() {
		return feeSumbmittedDate;
	}
	public void setfeeSumbmittedDate(String feeSumbmittedDate) {
		this.feeSumbmittedDate = feeSumbmittedDate;
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
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	
	public String getListDate() {
		return listDate;
	}
	public void setListDate(String listDate) {
		this.listDate = listDate;
	}
	@Override
	public String toString() {
		return "ExamList [id=" + id + ", batch=" + batch + ", feeSumbmittedDate=" + feeSumbmittedDate + ", paperStatus=" + paperStatus
				+ ", rollNo=" + rollNo + ", studentName=" + studentName + ", registrationNo=" + registrationNo
				+ ", fatherName=" + fatherName + ", semester=" + semester + ", department=" + department
				+ ", paperName=" + paperName + ", courseCode=" + courseCode + ", status=" + status + "]";
	}

	

}
