package com.liu.entity;

import java.util.List;

 /** 
 * @ClassName: PageBean 
 * @author: lyd
 * @date: 2018��2��5�� ����3:36:59 
 * @describe:��ҳbean
 */
public class PageBean {
	private int currentPage;//��ǰҳ
	private int pageSize;//ÿҳ��С
	private int count;//����
	private int totalPage;//��ҳ��
	private List<?> list;//�����б�
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
