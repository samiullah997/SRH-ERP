package com.sami.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String subject;
	private String email;
	@Column(length = 1000)
	private String message;
	public StudentFeedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentFeedback(int id, String name, String subject, String email, String message) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.email = email;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "StudentFeedback [id=" + id + ", name=" + name + ", subject=" + subject + ", email=" + email
				+ ", message=" + message + "]";
	}
	
}
