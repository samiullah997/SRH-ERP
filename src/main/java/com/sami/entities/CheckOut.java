package com.sami.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class CheckOut {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private Students studentsId;
	@OneToOne
	private Bus busId;
	private int seatNo;
	private Date checkOutDate;
	private float totalFee;
	private float submitFee;
	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckOut(int id, Students studentsId, Bus busId, int seatNo, Date checkOutDate, float totalFee,
			float submitFee) {
		super();
		this.id = id;
		this.studentsId = studentsId;
		this.busId = busId;
		this.seatNo = seatNo;
		this.checkOutDate = checkOutDate;
		this.totalFee = totalFee;
		this.submitFee = submitFee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Students getStudentsId() {
		return studentsId;
	}
	public void setStudentsId(Students studentsId) {
		this.studentsId = studentsId;
	}
	public Bus getBusId() {
		return busId;
	}
	public void setBusId(Bus busId) {
		this.busId = busId;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public float getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}
	public float getSubmitFee() {
		return submitFee;
	}
	public void setSubmitFee(float submitFee) {
		this.submitFee = submitFee;
	}
	@Override
	public String toString() {
		return "CheckOut [id=" + id + ", studentsId=" + studentsId + ", busId=" + busId + ", seatNo=" + seatNo
				+ ", checkOutDate=" + checkOutDate + ", totalFee=" + totalFee + ", submitFee=" + submitFee + "]";
	}
	
	

}
