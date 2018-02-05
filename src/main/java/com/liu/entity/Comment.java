package com.liu.entity;

import java.util.Date;

 /** 
 * @ClassName: Comment 
 * @author: lyd
 * @date: 2018年2月5日 下午3:22:11 
 * @describe:评论信息
 */
public class Comment {
	private Integer cid;//评论id
	private String content;//评论内容
	private Integer aid;//文章id
	private Integer uid;//用户id
	private Date date;//回复日期
	private User replyer;//回复者信息
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
