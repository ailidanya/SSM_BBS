package com.liu.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.CommentDao;
import com.liu.entity.Comment;
import com.liu.entity.Floor;
import com.liu.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Resource
	private CommentDao commentDao;
	public int addComment(String content, Integer aid, Integer uid, Timestamp timestamp) {
		// TODO Auto-generated method stub
		return commentDao.addComment(content, aid, uid, timestamp);
	}

	public List<Comment> findComment(Integer aid, Integer uid) {
		// TODO Auto-generated method stub
		return commentDao.findComment(aid, uid);
	}

	public int getCommentCount(Integer aid) {
		// TODO Auto-generated method stub
		return commentDao.getCommentCount(aid);
	}

	public List<Floor> findFloorComment(Integer aid, Integer cid) {
		// TODO Auto-generated method stub
		return commentDao.findFloorComment(aid, cid);
	}

	public int addFlooComment(Integer aid, Integer cid, Integer uid, String content) {
		// TODO Auto-generated method stub
		return commentDao.addFloorComment(aid, cid, uid, content);
	}

}
