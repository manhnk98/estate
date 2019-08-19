package com.nkm.paging;

public class PageRequest implements Pageble {

	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;

	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return page;
	}

	@Override
	public Integer getOffset() {
		// page = 1
		// maxPageItem = 2
		// => return 0*2
		
		// page = 2
		// maxPageItem = 2
		// => return 2
		if (page != null && maxPageItem != null) {
			return (page - 1) * maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		return sorter;
	}

}
