package com.liu.entity;

 /** 
 * @ClassName: Floor 
 * @author: lyd
 * @date: 2018��2��5�� ����3:23:34 
 * @describe:¥��¥������Ϣ
 */
public class Floor {
	private Integer fid;//id
	private Integer cid;//������Ϣid
	private Integer uid;//�û�Id
	private String content;//¥��¥�ظ���Ϣ
	private User floorReplyer;//�ظ�����Ϣ
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
