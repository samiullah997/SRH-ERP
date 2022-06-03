package com.sami.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="student_registration")
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	public String imageUrl;
	public String rollNo;
	public String firstName;
	public String lastName;
	public String fatherName;
	public String department;
	public String program;
	public String yearOfSubmission;
	public String dateOfBirth;
	@Column(unique = true)
	public String cnicNo;
	@Column(unique = true)
	public String registrationNo;
	@Column(length = 1000)
	public String permanentAddress;
	@Column(length = 1000)
	public String maillingAddress;
	public String phone;
	public String email;
	public String domicile;
	public String nationality;
	public String batch;
	public Date date;
	
	
	
	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Students(int id, String imageUrl, String rollNo, String firstName, String lastName, String fatherName,
			String department, String program, String yearOfSubmission, String dateOfBirth, String cnicNo,
			String registrationNo, String permanentAddress, String maillingAddress, String phone, String email,
			String domicile, String nationality, String batch, Date date) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName = fatherName;
		this.department = department;
		this.program = program;
		this.yearOfSubmission = yearOfSubmission;
		this.dateOfBirth = dateOfBirth;
		this.cnicNo = cnicNo;
		this.registrationNo = registrationNo;
		this.permanentAddress = permanentAddress;
		this.maillingAddress = maillingAddress;
		this.phone = phone;
		this.email = email;
		this.domicile = domicile;
		this.nationality = nationality;
		this.batch = batch;
		this.date = date;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getRollNo() {
		return rollNo;
	}



	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFatherName() {
		return fatherName;
	}



	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getProgram() {
		return program;
	}



	public void setProgram(String program) {
		this.program = program;
	}



	public String getYearOfSubmission() {
		return yearOfSubmission;
	}



	public void setYearOfSubmission(String yearOfSubmission) {
		this.yearOfSubmission = yearOfSubmission;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getCnicNo() {
		return cnicNo;
	}



	public void setCnicNo(String cnicNo) {
		this.cnicNo = cnicNo;
	}



	public String getRegistrationNo() {
		return registrationNo;
	}



	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}



	public String getPermanentAddress() {
		return permanentAddress;
	}



	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}



	public String getMaillingAddress() {
		return maillingAddress;
	}



	public void setMaillingAddress(String maillingAddress) {
		this.maillingAddress = maillingAddress;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDomicile() {
		return domicile;
	}



	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}



	public String getNationality() {
		return nationality;
	}



	public void setNationality(String nationality) {
		this.nationality = nationality;
	}



	public String getBatch() {
		return batch;
	}



	public void setBatch(String batch) {
		this.batch = batch;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Students [id=" + id + ", imageUrl=" + imageUrl + ", rollNo=" + rollNo + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", fatherName=" + fatherName + ", department=" + department + ", program="
				+ program + ", yearOfSubmission=" + yearOfSubmission + ", dateOfBirth=" + dateOfBirth + ", cnicNo="
				+ cnicNo + ", registrationNo=" + registrationNo + ", permanentAddress=" + permanentAddress
				+ ", maillingAddress=" + maillingAddress + ", phone=" + phone + ", email=" + email + ", domicile="
				+ domicile + ", nationality=" + nationality + ", batch=" + batch + ", date=" + date + "]";
	}


	


	
}
