package com.nkm.builder;

public class UserSearchBuilder {
	private String name;
	private String role;
	private String buildingId;
	private String customerId;

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public String getBuildingId() {
		return buildingId;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public UserSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.role = builder.role;
		this.buildingId = builder.buildingId;
		this.customerId = builder.customerId;
	}

	public static class Builder {
		private String name;
		private String role;
		private String buildingId;
		private String customerId;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setRole(String role) {
			this.role = role;
			return this;
		}

		public Builder setBuildingId(String buildingId) {
			this.buildingId = buildingId;
			return this;
		}
		
		public Builder setCustomerId(String customerId) {
			this.customerId = customerId;
			return this;
		}

		public UserSearchBuilder build() {
			return new UserSearchBuilder(this);
		}

	}
}
