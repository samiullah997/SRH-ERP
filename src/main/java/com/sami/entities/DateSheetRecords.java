package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DateSheetRecords {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstPaperNameOne;
	private String firstPaperNameTwo;
	private String secondPaperNameOne;
	private String secondPaperNameTwo;
	private String semester;
	private String date;
	
	public DateSheetRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DateSheetRecords(int id,
			String firstPaperNameOne,
			String firstPaperNameTwo,
			String secondPaperNameOne,
			String secondPaperNameTwo,
			String semester, String date) {
		super();
		this.id = id;
		this.firstPaperNameOne = firstPaperNameOne;
		this.firstPaperNameTwo = firstPaperNameTwo;
		this.secondPaperNameOne = secondPaperNameOne;
		this.secondPaperNameTwo = secondPaperNameTwo;
		this.semester = semester;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstPaperNameOne() {
		return firstPaperNameOne;
	}

	public void setFirstPaperNameOne(String firstPaperNameOne) {
		this.firstPaperNameOne = firstPaperNameOne;
	}

	public String getFirstPaperNameTwo() {
		return firstPaperNameTwo;
	}

	public void setFirstPaperNameTwo(String firstPaperNameTwo) {
		this.firstPaperNameTwo = firstPaperNameTwo;
	}

	public String getSecondPaperNameOne() {
		return secondPaperNameOne;
	}

	public void setSecondPaperNameOne(String secondPaperNameOne) {
		this.secondPaperNameOne = secondPaperNameOne;
	}

	public String getSecondPaperNameTwo() {
		return secondPaperNameTwo;
	}

	public void setSecondPaperNameTwo(String secondPaperNameTwo) {
		this.secondPaperNameTwo = secondPaperNameTwo;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DateSheetRecords [id=" + id + ", firstPaperNameOne=" + firstPaperNameOne + ", firstPaperNameTwo="
				+ firstPaperNameTwo + ", secondPaperNameOne=" + secondPaperNameOne + ", secondPaperNameTwo="
				+ secondPaperNameTwo + ", semester=" + semester + ", date=" + date + "]";
	}

	
	
}
