package com.liu.service.Impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.UserDao;
import com.liu.entity.User;
import com.liu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	public int addUser(String username, String password, String headimg) {
		// TODO Auto-generated method stub
		return userDao.addUser(username, password, headimg);
	}

	public User findUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.findUser(username, password);
	}

	public User getUserByID(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.getUserByID(uid);
	}

	public int updateHeadImg(String headImgName, Integer uid) {
		// TODO Auto-generated method stub
		return userDao.updateHeadImg(headImgName, uid);
	}

	public int updateUserInfo(Integer uid, String password) {
		// TODO Auto-generated method stub
		return userDao.updateUserInfo(uid, password);
	}

}
