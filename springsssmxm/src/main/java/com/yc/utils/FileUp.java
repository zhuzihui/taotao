package com.yc.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUp {
	
	private String allowedFileList = "gif,jpg,png,bmp";
	private String deniedFilesList = "jsp,asp,php,aspx,html,htm,exe,bat,js,py,sh";
	private long singleFileSize = 20 * 1024 *1024;
	private long totalMaxFileSize = 5 * singleFileSize;
	private String dirName="../upload/";
	private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private Map<String, String> map=new HashMap<String, String>();
	@RequestMapping("file")
	@ResponseBody
	public Map<String, String> springUpload(HttpServletRequest request)throws IllegalStateException, IOException {
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		//相当于SmartUpload
			multiRequest.setCharacterEncoding("utf-8");
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
				//文件名
					String filename=file.getOriginalFilename();
					//jsp文件中 <input /> 标签中的 name名字
					String oldname=file.getName();
					if(filename==null||filename==""){
						break;
					}
				//新的文件名	以时间命名，保证不会重复
					String newname = df.format(new Date())+filename.substring(filename.indexOf("."));
				//服务器中的位置
					String relativepath = dirName +newname;
					
					//需要得到这个	D:\apache-tomcat-8.0.46\webapps\erqi\
							  //D:/apache-tomcat-8.0.46/webapps/springtaotaoxm/
					String oldpath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
					
					String rootpath=oldpath.substring(1, oldpath.indexOf("WEB-INF"));
				//绝对路径
					//项目所在的绝对路径	运行后项目路径就是在服务器中的	:	/springtaotaoxm
					//String s1=request.getServletContext().getContextPath();
					//String rootpath = pageContext.getRequest().getRealPath("/");
					
					//+  ../upload/文件夹的路径
					String filepath = rootpath + dirName;
					//文件的绝对路径
					String absolutepath = filepath + newname;
					
					File f=new File(absolutepath);
					
					//IO原数据到文件
					file.transferTo(f);
					
					//没有服务器的地址，只有../upload/image 的地址
					map.put("image", relativepath);
					// 图片的绝对路径   前面包括服务器的地址
					map.put("path", absolutepath);
					
				}

			}

		}
		return map;
	}
}
