package com.app.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MYUser")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String emailId;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String password;

	public User() {

	}

	public User(String emailId, String phoneNumber, String firstName, String lastName, String password) {
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + "]";
	}

}
