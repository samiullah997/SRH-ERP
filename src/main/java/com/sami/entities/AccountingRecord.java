package com.sami.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AccountingRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	private String semester;
	private String type;
	private String slipNumber;
	private int totalAmount;
	private int submitAmount;
	private Date date;
	@OneToOne
	public Students studentsId;
	public AccountingRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountingRecord(int accountId, String semester, String type, int totalAmount, int submitAmount, Date date,
			Students studentsId,String slipNumber) {
		super();
		this.accountId = accountId;
		this.semester = semester;
		this.type = type;
		this.totalAmount = totalAmount;
		this.submitAmount = submitAmount;
		this.date = date;
		this.studentsId = studentsId;
		this.slipNumber=slipNumber;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getSubmitAmount() {
		return submitAmount;
	}
	public void setSubmitAmount(int submitAmount) {
		this.submitAmount = submitAmount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Students getStudentsId() {
		return studentsId;
	}
	public void setStudentsId(Students studentsId) {
		this.studentsId = studentsId;
	}
	
	public String getSlipNumber() {
		return slipNumber;
	}
	public void setSlipNumber(String slipNumber) {
		this.slipNumber = slipNumber;
	}
	@Override
	public String toString() {
		return "AccountingRecord [accountId=" + accountId + ", semester=" + semester + ", type=" + type
				+ ", totalAmount=" + totalAmount + ", submitAmount=" + submitAmount + ", date=" + date + ", studentsId="
				+ studentsId + "]";
	}
	

}
