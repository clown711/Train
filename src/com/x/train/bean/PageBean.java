package com.x.train.bean;


public class PageBean {
	private int page;//当前页码
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//分页（每页几条信息）
	
	public PageBean() {
		super();
	}

	public PageBean(int page, int totalCount, int totalPage, int limit) {
		super();
		this.page = page;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
