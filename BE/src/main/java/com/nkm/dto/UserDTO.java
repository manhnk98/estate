package com.nkm.dto;

import java.util.List;

import com.nkm.entity.RoleEntity;

public class UserDTO extends BaseDTO<UserDTO> {

	private String userName;
	private String password;
	private String fullName;
	private Integer status;
	private RoleEntity role;
//	private List<BuildingDTO> buildings;
	private String checked = "";
//	private List<CustomerDTO> customers;
	
//	public List<CustomerDTO> getCustomers() {
//		return customers;
//	}
//	public void setCustomers(List<CustomerDTO> customers) {
//		this.customers = customers;
//	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
//	public List<BuildingDTO> getBuildings() {
//		return buildings;
//	}
//	public void setBuildings(List<BuildingDTO> buildings) {
//		this.buildings = buildings;
//	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
