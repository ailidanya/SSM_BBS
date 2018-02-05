package com.liu.utils;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;



 /** 
 * @ClassName: LogUtils 
 * @author: lyd
 * @date: 2018年2月5日 下午4:29:06 
 * @describe:日志工具类
 */
public class LogUtils {
	private static boolean FLAG=true;
	static Logger logger=LoggerFactory.getLogger(LogUtils.class);
	public static void info(String msg)
	{
		if(FLAG)
		{
			logger.info(msg);
		}
	}
	public static void info(String format, Object... arguments)
	{
		if(FLAG){
			logger.info(format,arguments);
		}
	}
}
