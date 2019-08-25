package com.nkm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseDTO<T> {
	private Long id;
	private Date createddate;
	private Date modifieddate;
	private String createdby;
	private String modifiedby;
	private int maxPageItem;
	private int page;
	private String action;
	private List<T> listResults = new ArrayList<>();
	private Long[] ids;
	private int totalPage;
	private Long totalItem;
	private String sortName;
	private String sortBy;

	
	
	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(int maxPageItem) {
		this.maxPageItem = maxPageItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public List<T> getListResults() {
		return listResults;
	}

	public void setListResults(List<T> listResults) {
		this.listResults = listResults;
	}
	

}
