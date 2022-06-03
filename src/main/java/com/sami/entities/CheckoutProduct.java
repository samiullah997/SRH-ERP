package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CheckoutProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sellingQuantity;
	private int sellingPrice;
	private String customerName;
	@OneToOne
	private Products productsid;
	public CheckoutProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckoutProduct(int id, String sellingQuantity, int sellingPrice, String customerName, Products productsid) {
		super();
		this.id = id;
		this.sellingQuantity = sellingQuantity;
		this.sellingPrice = sellingPrice;
		this.customerName = customerName;
		this.productsid = productsid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellingQuantity() {
		return sellingQuantity;
	}
	public void setSellingQuantity(String sellingQuantity) {
		this.sellingQuantity = sellingQuantity;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Products getProductsid() {
		return productsid;
	}
	public void setProductsid(Products productsid) {
		this.productsid = productsid;
	}
	@Override
	public String toString() {
		return "CheckoutProduct [id=" + id + ", sellingQuantity=" + sellingQuantity + ", sellingPrice=" + sellingPrice
				+ ", customerName=" + customerName + ", productsid=" + productsid + "]";
	}
	
	
}
