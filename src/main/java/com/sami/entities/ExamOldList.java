package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExamOldList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String exam;
	private String listDate;
	public ExamOldList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamOldList(int id, String exam, String listDate) {
		super();
		this.id = id;
		this.exam = exam;
		this.listDate = listDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "examOldList [id=" + id + ", exam=" + exam + ", listDate=" + listDate + "]";
	}
	
	

}
