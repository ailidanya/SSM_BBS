package com.liu.test;

import java.sql.Timestamp;

import org.junit.Test;
import javax.annotation.Resource;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liu.service.Impl.ArticleServiceImpl;
import com.liu.service.Impl.CommentServiceImpl;
import com.liu.service.Impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)	
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class Test2 {
	@Resource
	private UserServiceImpl userServiceImpl;
	@Resource
	private ArticleServiceImpl articleServiceImpl;
	@Resource
	private CommentServiceImpl commentServiceImpl;
	@Test
	public void test(){
		Timestamp timestamp=new Timestamp(1);
		System.out.println(articleServiceImpl.getArticleList().get(0).getAuthor().getUsername());
	}
}
