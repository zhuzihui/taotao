package com.yc.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.JsonModel;
import com.yc.bean.PageHelperData;
import com.yc.bean.TbContent;
import com.yc.service.ContentService;
import com.yc.utils.FileUp;

@Controller
@RequestMapping(value="/content")
public class ContentController {
	
	@Resource(name="contentServiceImpl")
	private ContentService contentService;
	
	//显示分类
	@RequestMapping(value="/query/list")
	@ResponseBody
	public PageHelperData list(int page,int rows){
		//应该是配置文件与mysql关联了   查询之前自动匹配查询哪些数据
		PageHelper.startPage(page, rows);
		
		//因为加了	pagehelper 下面查出来的这个list结构发生了改变，包括其调用的service的方法中查出来的list结构也发生了变化
		List<TbContent> list=contentService.getContentList();
		PageHelperData pageData=new PageHelperData();
		
		
		PageInfo<TbContent> tbInfo=new PageInfo<TbContent>(list);
		pageData.setRows(list);		
		pageData.setTotal(tbInfo.getTotal());
		return pageData;
	}
	
	
	//新增
	@RequestMapping(value="/save")
	@ResponseBody
	public JsonModel addContent(JsonModel json,TbContent tbcontent,HttpServletRequest request) throws IllegalStateException, IOException{
		System.out.println("进来了");
		FileUp fu=new FileUp();
		Map<String, String> map=fu.springUpload(request);
		try {
			tbcontent.setPic(map.get("image"));
			tbcontent.setCreated(new Date());
			tbcontent.setUpdated(new Date());
			contentService.addContent(tbcontent);
			json.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//修改
	@RequestMapping(value="/edit")
	@ResponseBody
	public JsonModel editContent(JsonModel json,TbContent tbcontent,HttpServletRequest request) throws IllegalStateException, IOException{
		FileUp fu=new FileUp();
		Map<String, String> map=fu.springUpload(request);
		try {
			tbcontent.setPic(map.get("image"));
			tbcontent.setUpdated(new Date());
			contentService.updataContent(tbcontent);
			json.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public JsonModel deleteConten(@RequestParam(value="ids") long id,JsonModel json){
		try {
			contentService.deleteContent(id);
			json.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
