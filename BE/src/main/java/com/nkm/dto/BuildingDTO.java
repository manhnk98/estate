package com.nkm.dto;

public class BuildingDTO extends BaseDTO<BuildingDTO> {

	private String name; // tên
	private String ward; // phường
	private String street; // đường
	private String structure; // kết cấu
	private String numberOfBasement; // số tầng hầm
	private String buildingArea; // diện tích sàn
	private String district; // quận - huyện
	private Integer costRent; // giá thuê
	private String costDescription; // mô tả chi phí
	private String serviceCost; // Chi phí dịch vụ
	private String carCost; // chi ô tô
	private String motorbikeCost; // phí xe máy
	private String overTimeCost; // phí ngoài giờ
	private String electricityCost; // tiền điện
	private String deposit; // đặt cọc
	private String payment; // thanh toán
	private String timeContract; // thời gian thuê
	private String timeDecorator; // thời gian trang trí
	private String managerName; // tên quản lý
	private String managerPhone; // số điện thoại quản lý
	private String direction; // hướng
	private String level; // hạng
	private String type; // loại
	private String address;
	private String rentArea;
	private Integer status;

	private String costRentFrom; // giá thuê từ
	private String costRentTo; // giá thuê đến
	private String areaRentFrom; // diện tích từ
	private String areaRentTo; // diện tích đến
	private String[] buildingTypes = new String[] {};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(String numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getCostRent() {
		return costRent;
	}

	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}

	public String getOverTimeCost() {
		return overTimeCost;
	}

	public void setOverTimeCost(String overTimeCost) {
		this.overTimeCost = overTimeCost;
	}

	public String getElectricityCost() {
		return electricityCost;
	}

	public void setElectricityCost(String electricityCost) {
		this.electricityCost = electricityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTimeContract() {
		return timeContract;
	}

	public void setTimeContract(String timeContract) {
		this.timeContract = timeContract;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public void setCostRentFrom(String costRentFrom) {
		this.costRentFrom = costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public void setCostRentTo(String costRentTo) {
		this.costRentTo = costRentTo;
	}

	public String getAreaRentFrom() {
		return areaRentFrom;
	}

	public void setAreaRentFrom(String areaRentFrom) {
		this.areaRentFrom = areaRentFrom;
	}

	public String getAreaRentTo() {
		return areaRentTo;
	}

	public void setAreaRentTo(String areaRentTo) {
		this.areaRentTo = areaRentTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public void setBuildingTypes(String[] buildingTypes) {
		this.buildingTypes = buildingTypes;
	}

	public String getAddress() {
		return this.street + ", " + this.ward;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
