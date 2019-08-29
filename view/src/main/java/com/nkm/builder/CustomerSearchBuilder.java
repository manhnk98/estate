package com.nkm.builder;

public class CustomerSearchBuilder {

	private String fullName;
	private String phoneNumber;
	private String email;
	private String idStaff;

	public String getIdStaff() {
		return idStaff;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public CustomerSearchBuilder(Builder builder){
		this.fullName = builder.fullName;
		this.phoneNumber = builder.phoneNumber;
		this.email = builder.email;
		this.idStaff = builder.idStaff;
	}

	public static class Builder {
		private String fullName;
		private String phoneNumber;
		private String email;
		private String idStaff;

		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setIdStaff(String idStaff) {
			this.idStaff = idStaff;
			return this;
		}

		public CustomerSearchBuilder build(){
			return new CustomerSearchBuilder(this);
		}
	}
}
