package com.yc.service;

import java.util.List;

import com.yc.bean.TbContent;

public interface ContentService {
	//分页查询
	public List<TbContent> getContentList();
	
	//新增
	public void addContent(TbContent tbContent);
	
	//修改
	public void updataContent(TbContent tbContent);
	
	//删除	传进来的id：params
	public void deleteContent(long id);
	
	
	

}
