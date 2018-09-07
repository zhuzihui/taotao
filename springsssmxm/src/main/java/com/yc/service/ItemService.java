package com.yc.service;

import com.yc.bean.PageHelperData;
import com.yc.bean.TbItem;

public interface ItemService {
	TbItem getItemById(long id);
	
	PageHelperData getItemList(int page,int rows);//分页查询商品
	
	int save(TbItem tbItem);
}
