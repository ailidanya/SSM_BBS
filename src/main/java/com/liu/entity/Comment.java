package com.liu.entity;

import java.util.Date;

 /** 
 * @ClassName: Comment 
 * @author: lyd
 * @date: 2018��2��5�� ����3:22:11 
 * @describe:������Ϣ
 */
public class Comment {
	private Integer cid;//����id
	private String content;//��������
	private Integer aid;//����id
	private Integer uid;//�û�id
	private Date date;//�ظ�����
	private User replyer;//�ظ�����Ϣ
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getContent() {
		return content;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getReplyer() {
		return replyer;
	}
	public void setReplyer(User replyer) {
		this.replyer = replyer;
	}
}
