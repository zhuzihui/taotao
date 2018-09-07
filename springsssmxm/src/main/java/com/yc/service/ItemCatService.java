package com.yc.service;

import java.util.List;

import com.yc.bean.TbItemCat;

public interface ItemCatService {
	//类目查询
	List<TbItemCat> getItemCatList(Long parentId);//通过子类id找到所有目录
}
