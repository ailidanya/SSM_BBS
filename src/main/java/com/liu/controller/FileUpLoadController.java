package com.liu.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.liu.config.Config;
import com.liu.utils.LogUtils;
import com.liu.utils.ResponseUtils;

@Controller
public class FileUpLoadController {
	@RequestMapping("/upload")
	public String upload(@RequestParam("file")MultipartFile file,
			HttpServletRequest request,HttpServletResponse response){
		String fileName=file.getOriginalFilename();
		String filePath=request.getServletContext().getRealPath(Config.DEFAULT_UPLOAD_ADDRESS);
		System.out.println("fileName:"+fileName);
		System.out.println("filePath:"+filePath);
		LogUtils.info("文件名:{},文件存储路径:{}",fileName,filePath);
		try{
			file.transferTo(new File(filePath+fileName));		
		}catch (Exception e) {
			// TODO: handle exception
			LogUtils.info("文件上传失败!"+e);
			e.printStackTrace();
		}
		ResponseUtils.write(response, request.getContextPath()+Config.DEFAULT_UPLOAD_ADDRESS+fileName);
		return null;
	}
}
