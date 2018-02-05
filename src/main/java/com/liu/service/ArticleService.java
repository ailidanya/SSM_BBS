package com.liu.service;

import java.sql.Timestamp;
import java.util.List;

import com.liu.entity.Article;
import com.liu.entity.PageBean;

public interface ArticleService {
	public List<Article> getArticleList();
	public Article getArticleByID(Integer aid);
	public int addArticle(String title,String content,Timestamp timestamp,Integer uid,String lable);
	public List<Article> getArticleByUID(Integer uid);
	public int deleteArticleByID(Integer aid);
	public PageBean getArticlePageList(int currentPage,int pageSize);
	public List<Article> searchArticleByKey(String key);
	public List<Article> getArticleListByStatus(List<Integer> statusList) ;
}
