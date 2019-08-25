package com.nkm.entity;

import java.util.Date;

import com.nkm.annotation.Column;
import com.nkm.annotation.Entity;
import com.nkm.annotation.Table;
@Entity
@Table(name = "assignmentcustomer")
public class AssignmentCustomerEntity {
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "createddate")
	private Date createddate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	
}
