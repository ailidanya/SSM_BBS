package com.liu.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.liu.config.Config;
import com.liu.entity.Article;
import com.liu.entity.User;
import com.liu.service.ArticleService;
import com.liu.service.CommentService;
import com.liu.service.UserService;
import com.liu.utils.LogUtils;
import com.liu.utils.StringUtils;
import com.qiniu.storage.model.DefaultPutRet;

 /** 
 * @ClassName: UserController 
 * @author: lyd
 * @date: 2018年2月5日 下午3:31:35 
 * @describe:用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	@RequestMapping("/regist")
	@ResponseBody//可以进行返回Map即JSON
	public Map<String, String> regist(//注册
			@RequestParam("username")String username,
			@RequestParam("password")String password){
		Map<String, String> map=new HashMap<String, String>();
		if(userService.findUser(username, null)!=null){
			map.put("data", "用户名已存在");
			return map;
		}
		String headimg=Config.DEFAULT_HEADIMG_ADDRESS;
		int result=userService.addUser(username, StringUtils.MD5(password), headimg);
		if(result>0)
		{
			LogUtils.info("注册成功!用户名:{},密码{}",username,password);
			map.put("data", "注册成功,请重新登陆!");
		}else
		{
			LogUtils.info("注册失败!");
			map.put("data", "注册失败!");
		}
		return map;
	}
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(HttpSession session,@RequestParam("username")String username,//登录
			@RequestParam("password")String password){
		User user=userService.findUser(username, StringUtils.MD5(password));
		Map<String,String> map=new HashMap<String, String>();
		if(user!=null){
			LogUtils.info("登陆成功！用户名:{},密码:{}",username,password);
			map.put("data", "登陆成功!");
			session.setAttribute("user", user);
		}else
		{
			LogUtils.info("登陆失败！");
			map.put("data", "登陆失败!");
		}
		return map;
	}
	@RequestMapping("/manager/{uid}")
	public ModelAndView getUserManager(@PathVariable("uid")Integer uid){//获取管理者信息
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.getUserByID(uid);
		List<Article> articleList=articleService.getArticleByUID(uid);
		modelAndView.addObject("userArticle",articleList);
		modelAndView.addObject("u",user);
		modelAndView.setViewName("user/userManager");
		return modelAndView;
	}
	@RequestMapping("/info/{uid}")
	public ModelAndView getUserInfo(@PathVariable("uid")Integer uid){//获取信息
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.getUserByID(uid);
		modelAndView.addObject("uInfo",user);
		modelAndView.setViewName("/user/userInfo");
		return modelAndView;
	}
	@RequestMapping("/headimg/{uid}")
	public String updateHeadImg(HttpServletRequest request,//修改头像
			@RequestParam("file")MultipartFile file,
			@PathVariable("uid")Integer uid) throws IllegalStateException, IOException{
		if(file.isEmpty())
			return null;
		String fileName=file.getOriginalFilename();//获取头像文件名
		String realpath=request.getServletContext().getRealPath("");//获取路径
		file.transferTo(new File(realpath+"\\resources\\imgs\\"+fileName));//复制文件到指定位置
		int result=userService.updateHeadImg(Config.HEADIMG_ADDRESS+fileName, uid);
		if(result>0){
			LogUtils.info("成功更新uid为{}的用户头像，文件名{}",uid);
		}else{
			LogUtils.info("更新偷笑失败!");
		}
		request.getSession().setAttribute("user", userService.getUserByID(uid));
		return "redirect:/index.jsp";
	}
	@RequestMapping("/update/{uid}")
	@ResponseBody
	public Map<String, String> updateUserInfo(HttpSession session,@PathVariable("uid")Integer uid,//修改用户信息
			@RequestParam("password")String password){
		Map<String, String>map=new HashMap<String, String>();
		User user=(User)session.getAttribute("user");
		if(user.getUid()!=uid){
			map.put("data", "只能修改自己的信息");
			return map;
		}
		int result=userService.updateUserInfo(uid, StringUtils.MD5(password));
		if(result>0){
			LogUtils.info("成功更新id为{}的用户信息！新密码:{}",uid,password);
			map.put("data", "信息修改成功!");
		}else
		{
			map.put("data", "信息修改失败!");
		}
		return map;
	}
	@RequestMapping("/exit")
	public String exit(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
}
