package com.liu.entity;

import java.util.Date;

 /** 
 * @ClassName: Article 
 * @author: lyd
 * @date: 2018��2��5�� ����3:21:57 
 * @describe:������Ϣ
 */
public class Article 
{
	private Integer aid;//����id
	private String title;//���±���
	private String content;//��������
	private Date date;//��������
	private Integer uid;//�û�Id
	private String lable;//���±�ǩ
	private Integer status;//�ö��Ӿ�״̬
	private User author;//�������û�
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	
	
}
