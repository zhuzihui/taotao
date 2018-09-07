package com.yc.service;

import java.util.List;

import com.yc.bean.TbContentCategory;
import com.yc.bean.TbItemCat;

public interface ContentCatService {
	//类目查询
		List<TbContentCategory> getContentCatList(Long parentId);//通过子类id找到所有目录
		//类目添加
		long insertCat(TbContentCategory tbContentCategory);
		//类目删除
		void deleteCat(long id);
		//重命名
		void renameCat(long id,String name);
}
