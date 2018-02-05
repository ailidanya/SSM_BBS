package com.liu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /** 
 * @ClassName: StringUtils 
 * @author: lyd
 * @date: 2018年2月5日 下午4:36:47 
 * @describe:字符串工具类
 */
public class StringUtils {
	public static final String EMPTY_STRING="";
	public static boolean isEmpty(String str){
		return str==null||str.length()==0;
	}
	public static boolean isBlank(String str){
		int strLen;
		if(str==null||(strLen=str.length())==0){
			return true;
		}
		for(int i=0;i<strLen;i++){
			if((Character.isWhitespace(str.charAt(i))==false)){
				return false;
			}
		}
		return true;
	}
	public static String replaceHtml(String html){
		if(StringUtils.isBlank(html)){
			return StringUtils.EMPTY_STRING;
		}
		String regEx="<.+?>";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(html);
		String s=m.replaceAll(StringUtils.EMPTY_STRING);
		return s;
	}
	public final static String MD5(String str){//MD5加密
		try
		{
			if(!StringUtils.isEmpty(str)){
				MessageDigest messageDigest=MessageDigest.getInstance("MD5");
				byte[] digest=messageDigest.digest(str.getBytes("utf-8"));
				return new BigInteger(digest).toString(16);
			}else
			{
				return "";
			}
		}catch (Exception e) 
			{
				throw new RuntimeException("字符串加密失败!"+e);
			}
		
	}
}
