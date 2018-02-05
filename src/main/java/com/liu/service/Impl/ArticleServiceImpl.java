package com.liu.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.dao.ArticleDao;
import com.liu.entity.Article;
import com.liu.entity.PageBean;
import com.liu.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDao articleDao;
	public List<Article> getArticleList() {
		// TODO Auto-generated method stub
		return articleDao.getArticleList();
	}

	public Article getArticleByID(Integer aid) {
		// TODO Auto-generated method stub
		return articleDao.getArticleByID(aid);
	}

	public int addArticle(String title, String content, Timestamp timestamp, Integer uid, String lable) {
		// TODO Auto-generated method stub
		return articleDao.addArticle(title, content, timestamp, uid, lable);
	}

	public List<Article> getArticleByUID(Integer uid) {
		// TODO Auto-generated method stub
		return articleDao.getArticleByUID(uid);
	}

	public int deleteArticleByID(Integer aid) {
		// TODO Auto-generated method stub
		return articleDao.deleteArticleByID(aid);
	}

	public PageBean getArticlePageList(int currentPage, int pageSize) {
		int count=articleDao.getArticleList().size();
		int totalPage=(int) Math.ceil(count*1.0/pageSize);
		List<Article> articleList=articleDao.getArticlePageList((currentPage-1)*pageSize, pageSize);
		PageBean pageBean=new PageBean(currentPage, pageSize, count, totalPage, articleList);
		return pageBean;
	}

	public List<Article> searchArticleByKey(String key) {
		// TODO Auto-generated method stub
		return articleDao.searchArticleByKey(key);
	}

	public List<Article> getArticleListByStatus(List<Integer> statusList) {
		// TODO Auto-generated method stub
		return articleDao.getArticleListByStatus(statusList);
	}

}
