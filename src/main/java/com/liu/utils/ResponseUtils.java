package com.liu.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
	public static void write(HttpServletResponse response,Object o){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try
		{
			out=response.getWriter();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			if(out!=null)
			{
				out.println(o.toString());
				out.flush();
				out.close();
			}
		}
	}
}
