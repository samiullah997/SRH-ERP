package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int quantity;
	private String availibile;
	private String edition;
	private String autherName;
	private String department;
	private int totalQuantity;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String title, int quantity, String availibile, String edition, String autherName,
			String department) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.availibile = availibile;
		this.edition = edition;
		this.autherName = autherName;
		this.department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAvailibile() {
		return availibile;
	}
	public void setAvailibile(String availibile) {
		this.availibile = availibile;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getAutherName() {
		return autherName;
	}
	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", quantity=" + quantity + ", availibile=" + availibile + ", edition="
				+ edition + ", autherName=" + autherName + ", department=" + department + "]";
	}
	
}
