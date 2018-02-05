package com.liu.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liu.config.Config;
import com.liu.entity.Article;
import com.liu.entity.Comment;
import com.liu.entity.Floor;
import com.liu.entity.User;
import com.liu.service.ArticleService;
import com.liu.service.CommentService;
import com.liu.service.UserService;
import com.liu.utils.LogUtils;
import com.liu.utils.StringUtils;
//����Controller
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@RequestMapping("/list/{currentPage}")
	public ModelAndView getArticlePageList(HttpSession session,@PathVariable("currentPage")int currentPage)//չʾ�����б�
	{
		ModelAndView mav=new ModelAndView();
		int pageSize=Config.DEFAULT_PAGESIZE;//��ҳ��������
		mav.addObject("articlePageBean",articleService.getArticlePageList(currentPage, pageSize));
		List<Integer> statusList=new ArrayList<Integer>();
		statusList.add(Config.STATUS_TOP);
		statusList.add(Config.STATUS_TOP_HOT);
		mav.addObject("topArticle",articleService.getArticleListByStatus(statusList));
		if(session.getAttribute("user")!=null){
			User user=(User)session.getAttribute("user");
			session.setAttribute("user", userService.getUserByID(user.getUid()));
		}
		mav.setViewName("article/articleList");
		return mav;
	}
	@RequestMapping("/details/{aid}")
	public ModelAndView getArticleByID(@PathVariable("aid")Integer aid)//����aid��ȡ��ϸ������Ϣ
	{
		ModelAndView modelAndView=new ModelAndView();
		Article article=articleService.getArticleByID(aid);//������Ϣ
		List<Comment> commentList=commentService.findComment(aid, null);//������Ϣ
		List<Floor> floorCommentList=commentService.findFloorComment(aid, null);//¥��¥��Ϣ
		modelAndView.addObject(article);
		modelAndView.addObject(commentList);
		modelAndView.addObject("Floor", floorCommentList);
		modelAndView.setViewName("article/articleContent");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/floor/add")
	public Map<String, String> findFloorComment(HttpSession session,//���¥��¥������Ϣ
			@RequestParam("aid")Integer aid,
			@RequestParam("cid")Integer cid,
			@RequestParam("uid")Integer uid,
			@RequestParam("content")String content){
		Map<String, String> map=new HashMap<String,String>();
		User user=(User) session.getAttribute("user");
		if(user.getUid()!=uid){
			map.put("data", "�ظ�ʧ��");
			return map;
		}
		int result=commentService.addFlooComment(aid, cid, uid, content);
		if(result>0)
		{
			LogUtils.info("¥��¥���۳ɹ�!����=>"+content);
			map.put("data","�ظ��ɹ�");
		}else
		{
			LogUtils.info("¥��¥����ʧ�ܣ�");
			map.put("data", "�ظ�ʧ�ܣ�");
		}
		return map;
	}
	@RequestMapping("/delete/{aid}")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession httpSession,@PathVariable("aid") Integer aid){//����aidɾ������
		Map<String, String> map=new HashMap<String, String>();
		User user=(User) httpSession.getAttribute("user");
		Integer uid=articleService.getArticleByID(aid).getAuthor().getUid();
		if(user.getUid()!=uid){
			map.put("data", "ֻ��ɾ���Լ�������");
			return map;
		}
		int result=articleService.deleteArticleByID(aid);
		if(result>0){
			LogUtils.info("�ɹ�ɾ��idΪ{}������!",aid);
			map.put("data", "ɾ���ɹ�");
		}else{
			LogUtils.info("ɾ��ʧ��idΪ{}������!",aid);
			map.put("data", "ɾ��ʧ��!");
		}
		return map;
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> addArticle(HttpSession session,//�������
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "uid") Integer uid,
			@RequestParam(value = "lable") String lable){
		Map<String, String> map=new HashMap<String, String>();
		User user=(User)session.getAttribute("user");
		if(user==null){
			map.put("data", "���¼���ٷ���!");
			return map;
		}
		if(StringUtils.isEmpty(title)||StringUtils.isBlank(title)){
			map.put("data", "���ⲻ��Ϊ��!");
		}
		int result=articleService.addArticle(title, content, new Timestamp(new Date().getTime()), uid, lable);
		if(result>0){
			LogUtils.info("�����ɹ�,����:{},���ݳ���:{}",title,content.length());
			map.put("data", "�����ɹ�");
		}
		else
		{
			LogUtils.info("����ʧ��!");
		}
		return map;
	}
	@RequestMapping("/search")//����key��������
	public ModelAndView SearchArticle(@RequestParam("key")String key){
		ModelAndView modelAndView=new ModelAndView();
		List<Article> list=articleService.searchArticleByKey(key);
		LogUtils.info("��ѯ�ؼ���:{},����ѯ��{}����¼",key,list.size());
		modelAndView.addObject("key",key);
		modelAndView.addObject("resultList",list);
		modelAndView.setViewName("search");
		return modelAndView;
	}
}
