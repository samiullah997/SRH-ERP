package com.sami.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String productName;
	private String quantity;
	private String measurement;
	private int productPrice;
	private String supplier;
	private String sContact;
	private String sAddress;
	private Date date;
	@OneToOne
	private Categories categoriesId;
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Products(int id, String productName, String quantity, String measurement, int productPrice, String supplier,
			String sContact, String sAddress,Date date, Categories categoriesId) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.measurement = measurement;
		this.productPrice = productPrice;
		this.supplier = supplier;
		this.sContact = sContact;
		this.sAddress = sAddress;
		this.date=date;
		this.categoriesId = categoriesId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getsContact() {
		return sContact;
	}
	public void setsContact(String sContact) {
		this.sContact = sContact;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public Categories getCategoriesId() {
		return categoriesId;
	}
	public void setCategoriesId(Categories categoriesId) {
		this.categoriesId = categoriesId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", measurement="
				+ measurement + ", productPrice=" + productPrice + ", supplier=" + supplier + ", sContact=" + sContact
				+ ", sAddress=" + sAddress + ", categoriesId=" + categoriesId + "]";
	}
	
	

}
