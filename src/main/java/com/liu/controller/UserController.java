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
 * @date: 2018��2��5�� ����3:31:35 
 * @describe:�û�Controller
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
	@ResponseBody//���Խ��з���Map��JSON
	public Map<String, String> regist(//ע��
			@RequestParam("username")String username,
			@RequestParam("password")String password){
		Map<String, String> map=new HashMap<String, String>();
		if(userService.findUser(username, null)!=null){
			map.put("data", "�û����Ѵ���");
			return map;
		}
		String headimg=Config.DEFAULT_HEADIMG_ADDRESS;
		int result=userService.addUser(username, StringUtils.MD5(password), headimg);
		if(result>0)
		{
			LogUtils.info("ע��ɹ�!�û���:{},����{}",username,password);
			map.put("data", "ע��ɹ�,�����µ�½!");
		}else
		{
			LogUtils.info("ע��ʧ��!");
			map.put("data", "ע��ʧ��!");
		}
		return map;
	}
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(HttpSession session,@RequestParam("username")String username,//��¼
			@RequestParam("password")String password){
		User user=userService.findUser(username, StringUtils.MD5(password));
		Map<String,String> map=new HashMap<String, String>();
		if(user!=null){
			LogUtils.info("��½�ɹ����û���:{},����:{}",username,password);
			map.put("data", "��½�ɹ�!");
			session.setAttribute("user", user);
		}else
		{
			LogUtils.info("��½ʧ�ܣ�");
			map.put("data", "��½ʧ��!");
		}
		return map;
	}
	@RequestMapping("/manager/{uid}")
	public ModelAndView getUserManager(@PathVariable("uid")Integer uid){//��ȡ��������Ϣ
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.getUserByID(uid);
		List<Article> articleList=articleService.getArticleByUID(uid);
		modelAndView.addObject("userArticle",articleList);
		modelAndView.addObject("u",user);
		modelAndView.setViewName("user/userManager");
		return modelAndView;
	}
	@RequestMapping("/info/{uid}")
	public ModelAndView getUserInfo(@PathVariable("uid")Integer uid){//��ȡ��Ϣ
		ModelAndView modelAndView=new ModelAndView();
		User user=userService.getUserByID(uid);
		modelAndView.addObject("uInfo",user);
		modelAndView.setViewName("/user/userInfo");
		return modelAndView;
	}
	@RequestMapping("/headimg/{uid}")
	public String updateHeadImg(HttpServletRequest request,//�޸�ͷ��
			@RequestParam("file")MultipartFile file,
			@PathVariable("uid")Integer uid) throws IllegalStateException, IOException{
		if(file.isEmpty())
			return null;
		String fileName=file.getOriginalFilename();//��ȡͷ���ļ���
		String realpath=request.getServletContext().getRealPath("");//��ȡ·��
		file.transferTo(new File(realpath+"\\resources\\imgs\\"+fileName));//�����ļ���ָ��λ��
		int result=userService.updateHeadImg(Config.HEADIMG_ADDRESS+fileName, uid);
		if(result>0){
			LogUtils.info("�ɹ�����uidΪ{}���û�ͷ���ļ���{}",uid);
		}else{
			LogUtils.info("����͵Цʧ��!");
		}
		request.getSession().setAttribute("user", userService.getUserByID(uid));
		return "redirect:/index.jsp";
	}
	@RequestMapping("/update/{uid}")
	@ResponseBody
	public Map<String, String> updateUserInfo(HttpSession session,@PathVariable("uid")Integer uid,//�޸��û���Ϣ
			@RequestParam("password")String password){
		Map<String, String>map=new HashMap<String, String>();
		User user=(User)session.getAttribute("user");
		if(user.getUid()!=uid){
			map.put("data", "ֻ���޸��Լ�����Ϣ");
			return map;
		}
		int result=userService.updateUserInfo(uid, StringUtils.MD5(password));
		if(result>0){
			LogUtils.info("�ɹ�����idΪ{}���û���Ϣ��������:{}",uid,password);
			map.put("data", "��Ϣ�޸ĳɹ�!");
		}else
		{
			map.put("data", "��Ϣ�޸�ʧ��!");
		}
		return map;
	}
	@RequestMapping("/exit")
	public String exit(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
}
