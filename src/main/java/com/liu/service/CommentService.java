package com.liu.service;

import java.sql.Timestamp;
import java.util.List;

import com.liu.entity.Comment;
import com.liu.entity.Floor;

public interface CommentService {
	public int addComment(String content,Integer aid,Integer uid,Timestamp timestamp);
	public List<Comment> findComment(Integer aid,Integer uid);
	public int getCommentCount(Integer aid);
	public List<Floor> findFloorComment(Integer aid,Integer cid);
	public int addFlooComment(Integer aid,Integer cid,Integer uid,String content);
}
