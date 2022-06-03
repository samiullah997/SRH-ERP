package com.sami.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BookIssued {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String status;
	private Date issueDate;
	private Date returnDate;
	@OneToOne
	private Book bookId;
	@OneToOne
	private Students studentsId;
	
	public BookIssued() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookIssued(int id, String status, Date issueDate, Date returnDate, Book bookId, Students studentsId) {
		super();
		this.id = id;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.bookId = bookId;
		this.studentsId = studentsId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}

	public Students getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(Students studentsId) {
		this.studentsId = studentsId;
	}

	@Override
	public String toString() {
		return "BookIssued [id=" + id + ", status=" + status + ", issueDate=" + issueDate + ", returnDate=" + returnDate
				+ ", bookId=" + bookId + ", studentsId=" + studentsId + "]";
	}
	
	
}
