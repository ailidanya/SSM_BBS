package com.liu.service;

import com.liu.entity.User;

public interface UserService {
	public int addUser(String username,String password,String headimg);
	public User findUser(String username,String password);
	public User getUserByID(Integer uid);
	public int updateHeadImg(String headImgName,Integer uid);
	public int updateUserInfo(Integer uid,String password);
}
