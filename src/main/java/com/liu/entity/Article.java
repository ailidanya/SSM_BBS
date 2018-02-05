package com.liu.entity;

import java.util.Date;

 /** 
 * @ClassName: Article 
 * @author: lyd
 * @date: 2018年2月5日 下午3:21:57 
 * @describe:文章信息
 */
public class Article 
{
	private Integer aid;//文章id
	private String title;//文章标题
	private String content;//文章内容
	private Date date;//发布日期
	private Integer uid;//用户Id
	private String lable;//文章标签
	private Integer status;//置顶加精状态
	private User author;//发布的用户
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
