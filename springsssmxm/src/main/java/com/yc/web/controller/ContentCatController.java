package com.yc.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.bean.JsonModel;
import com.yc.bean.TbContentCategory;
import com.yc.service.ContentCatService;

@Controller
@RequestMapping(value="/content")
public class ContentCatController {
	
	@Resource(name="contentCatServiceImpl")
	private ContentCatService contentCatService;
	
	//显示
	@RequestMapping(value="/category/list")
	@ResponseBody
	public List<Map<String, Object>> showList(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<Map<String, Object>> castList=new ArrayList<Map<String, Object>>();
		List<TbContentCategory> list=contentCatService.getContentCatList(parentId);
		for (TbContentCategory tb : list) {
			Map<String, Object> node=new HashMap<String, Object>();
			node.put("id", tb.getId());
			node.put("text", tb.getName());
			node.put("state", tb.getIsParent()?"closed":"open");
			castList.add(node);
		}
		return castList;
	}
	
	//添加
	@RequestMapping(value="/category/create")
	@ResponseBody
	public JsonModel creatCat(JsonModel json,TbContentCategory tbContentCategory){
		try {
			tbContentCategory.setCreated(new Date());
			tbContentCategory.setIsParent(false);
			tbContentCategory.setSortOrder(1);
			tbContentCategory.setStatus(1);
			tbContentCategory.setUpdated(new Date());
			long id=contentCatService.insertCat(tbContentCategory);
			tbContentCategory.setId(id);
			json.setStatus(200);
			json.setData(tbContentCategory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	//删除
	@RequestMapping(value="/category/delete")
	@ResponseBody
	public void deleteCat(long id){
		System.out.println("进来了");
		contentCatService.deleteCat(id);
	}
	
	//重命名
	@RequestMapping(value="/category/update")
	@ResponseBody
	public void rename(long id,String name){
		try {
			contentCatService.renameCat(id, name);
			System.out.println("成功了");
		} catch (Exception e) {
			System.out.println("失败了");
			e.printStackTrace();
		}
		
	}
	
}
