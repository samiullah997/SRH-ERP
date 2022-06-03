package com.sami.entities;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SeatBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatBookId;
	private int seatNo;
	private Date date;
	@OneToOne
	private Bus busId;
	@OneToOne
	private Students studentsId;
	public SeatBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SeatBook(int seatBookId, Bus busId, int seatNo, Date date, Students studentsId) {
		super();
		this.seatBookId = seatBookId;
		this.busId = busId;
		this.seatNo = seatNo;
		this.date = date;
		this.studentsId = studentsId;
	}
	public int getSeatBookId() {
		return seatBookId;
	}
	public void setSeatBookId(int seatBookId) {
		this.seatBookId = seatBookId;
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
	@Override
	public String toString() {
		return "SeatBook [seatBookId=" + seatBookId + ", busNumber=" + busId + ", seatNo=" + seatNo + ", date="
				+ date + ", studentsId=" + studentsId + "]";
	}
	
	

}
