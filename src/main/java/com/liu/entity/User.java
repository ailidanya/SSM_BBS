package com.liu.entity;

 /** 
 * @ClassName: User 
 * @author: lyd
 * @date: 2018年2月5日 下午3:27:36 
 * @describe:用户信息
 */
public class User {
	private Integer uid;//id
	private String username;//用户名
	private String password;//用户密码
	private String headimg;//用户头像地址
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
