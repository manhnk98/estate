package com.nkm.entity;

import com.nkm.annotation.Column;
import com.nkm.annotation.Entity;
import com.nkm.annotation.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "need")
	private String need;

	@Column(name = "notes")
	private String notes;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
