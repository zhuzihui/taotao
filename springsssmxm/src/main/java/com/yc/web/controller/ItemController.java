package com.yc.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.bean.JsonModel;
import com.yc.bean.PageHelperData;
import com.yc.bean.TbItem;
import com.yc.service.ItemService;
import com.yc.utils.FileUp;

@Controller
public class ItemController {
	@Resource(name="itemServiceImpl")
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	//分页查询商品
	@RequestMapping("/item/list")
	@ResponseBody
	public PageHelperData getItemList(Integer page, Integer rows) {
		PageHelperData result = itemService.getItemList(page, rows);
		return result;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	//新增商品
	@RequestMapping(value="/item/save")
	@ResponseBody
	public JsonModel save(JsonModel json,TbItem tbItem,HttpServletRequest request) throws IllegalStateException, IOException{
		FileUp fileup=new FileUp();
		Map<String,String> map=fileup.springUpload(request);
		tbItem.setImage(map.get("image"));
		try {
			Date d=new Date();
			tbItem.setCreated(d);
			tbItem.setUpdated(d);
			itemService.save(tbItem);
			json.setStatus(200);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return json;
	}
	
	
}
