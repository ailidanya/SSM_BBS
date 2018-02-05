package com.liu.entity;

import java.util.List;

 /** 
 * @ClassName: PageBean 
 * @author: lyd
 * @date: 2018年2月5日 下午3:36:59 
 * @describe:分页bean
 */
public class PageBean {
	private int currentPage;//当前页
	private int pageSize;//每页大小
	private int count;//总数
	private int totalPage;//总页数
	private List<?> list;//文章列表
	public PageBean(int currentPage, int pageSize, int count, int totalPage, List<?> list) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.count = count;
		this.totalPage = totalPage;
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	
}
