package com.liu.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.service.CommentService;
import com.liu.service.UserService;
import com.liu.utils.LogUtils;

@Controller
@RequestMapping("/comment")
public class CommentController {
@Autowired
private CommentService commentService;
@Autowired
private UserService userService;
@RequestMapping("/add")
@ResponseBody
public Map<String, String> addComment(
		@RequestParam(value="content",required=false)String content,
		@RequestParam(value="aid") Integer aid,
		@RequestParam(value="uid")Integer uid){
	Map<String, String> map=new HashMap<String, String>();
	int result=commentService.addComment(content, aid, uid, new Timestamp(new Date().getTime()));
	if(result>0){
		LogUtils.info("回复成功！,内容为:"+content);
		map.put("data", "回复成功!");	
	}else{
		LogUtils.info("回复失败!");
		map.put("data", "回复失败!");		
		}
	return map;
	}
}
