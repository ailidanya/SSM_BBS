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
//文章Controller
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
	public ModelAndView getArticlePageList(HttpSession session,@PathVariable("currentPage")int currentPage)//展示文章列表
	{
		ModelAndView mav=new ModelAndView();
		int pageSize=Config.DEFAULT_PAGESIZE;//分页文章数量
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
	public ModelAndView getArticleByID(@PathVariable("aid")Integer aid)//根据aid获取明细文章信息
	{
		ModelAndView modelAndView=new ModelAndView();
		Article article=articleService.getArticleByID(aid);//文章信息
		List<Comment> commentList=commentService.findComment(aid, null);//评论信息
		List<Floor> floorCommentList=commentService.findFloorComment(aid, null);//楼中楼信息
		modelAndView.addObject(article);
		modelAndView.addObject(commentList);
		modelAndView.addObject("Floor", floorCommentList);
		modelAndView.setViewName("article/articleContent");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/floor/add")
	public Map<String, String> findFloorComment(HttpSession session,//添加楼中楼评论信息
			@RequestParam("aid")Integer aid,
			@RequestParam("cid")Integer cid,
			@RequestParam("uid")Integer uid,
			@RequestParam("content")String content){
		Map<String, String> map=new HashMap<String,String>();
		User user=(User) session.getAttribute("user");
		if(user.getUid()!=uid){
			map.put("data", "回复失败");
			return map;
		}
		int result=commentService.addFlooComment(aid, cid, uid, content);
		if(result>0)
		{
			LogUtils.info("楼中楼评论成功!内容=>"+content);
			map.put("data","回复成功");
		}else
		{
			LogUtils.info("楼中楼评论失败！");
			map.put("data", "回复失败！");
		}
		return map;
	}
	@RequestMapping("/delete/{aid}")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession httpSession,@PathVariable("aid") Integer aid){//根据aid删除文章
		Map<String, String> map=new HashMap<String, String>();
		User user=(User) httpSession.getAttribute("user");
		Integer uid=articleService.getArticleByID(aid).getAuthor().getUid();
		if(user.getUid()!=uid){
			map.put("data", "只能删除自己的帖子");
			return map;
		}
		int result=articleService.deleteArticleByID(aid);
		if(result>0){
			LogUtils.info("成功删除id为{}的帖子!",aid);
			map.put("data", "删除成功");
		}else{
			LogUtils.info("删除失败id为{}的帖子!",aid);
			map.put("data", "删除失败!");
		}
		return map;
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> addArticle(HttpSession session,//添加文章
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "uid") Integer uid,
			@RequestParam(value = "lable") String lable){
		Map<String, String> map=new HashMap<String, String>();
		User user=(User)session.getAttribute("user");
		if(user==null){
			map.put("data", "请登录后再发帖!");
			return map;
		}
		if(StringUtils.isEmpty(title)||StringUtils.isBlank(title)){
			map.put("data", "标题不能为空!");
		}
		int result=articleService.addArticle(title, content, new Timestamp(new Date().getTime()), uid, lable);
		if(result>0){
			LogUtils.info("发帖成功,标题:{},内容长度:{}",title,content.length());
			map.put("data", "发帖成功");
		}
		else
		{
			LogUtils.info("发帖失败!");
		}
		return map;
	}
	@RequestMapping("/search")//根据key搜索文章
	public ModelAndView SearchArticle(@RequestParam("key")String key){
		ModelAndView modelAndView=new ModelAndView();
		List<Article> list=articleService.searchArticleByKey(key);
		LogUtils.info("查询关键字:{},共查询到{}条记录",key,list.size());
		modelAndView.addObject("key",key);
		modelAndView.addObject("resultList",list);
		modelAndView.setViewName("search");
		return modelAndView;
	}
}
