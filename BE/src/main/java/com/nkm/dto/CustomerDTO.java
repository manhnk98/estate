package com.nkm.dto;

public class CustomerDTO extends BaseDTO<CustomerDTO> {
	
	private String fullName;
	private String phoneNumber;
	private String email;
	private String companyName;
	private String need;
	private String notes;
//	private List<UserEntity> users;
	
//	public List<UserEntity> getUsers() {
//		return users;
//	}
//	public void setUsers(List<UserEntity> users) {
//		this.users = users;
//	}
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
