package com.liu.entity;

 /** 
 * @ClassName: User 
 * @author: lyd
 * @date: 2018��2��5�� ����3:27:36 
 * @describe:�û���Ϣ
 */
public class User {
	private Integer uid;//id
	private String username;//�û���
	private String password;//�û�����
	private String headimg;//�û�ͷ���ַ
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
}
