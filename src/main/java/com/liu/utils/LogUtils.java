package com.liu.utils;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;



 /** 
 * @ClassName: LogUtils 
 * @author: lyd
 * @date: 2018��2��5�� ����4:29:06 
 * @describe:��־������
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
