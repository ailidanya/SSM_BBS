package com.liu.entity;

 /** 
 * @ClassName: Floor 
 * @author: lyd
 * @date: 2018年2月5日 下午3:23:34 
 * @describe:楼中楼评论信息
 */
public class Floor {
	private Integer fid;//id
	private Integer cid;//评论信息id
	private Integer uid;//用户Id
	private String content;//楼中楼回复信息
	private User floorReplyer;//回复者信息
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getFloorReplyer() {
		return floorReplyer;
	}
	public void setFloorReplyer(User floorReplyer) {
		this.floorReplyer = floorReplyer;
	}
	
}
