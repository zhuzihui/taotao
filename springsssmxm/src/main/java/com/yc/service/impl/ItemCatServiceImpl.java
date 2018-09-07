package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.TbItemCat;
import com.yc.bean.TbItemCatExample;
import com.yc.bean.TbItemCatExample.Criteria;
import com.yc.mapper.TbItemCatMapper;
import com.yc.service.ItemCatService;

//类目查询
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}
	
	public void setItemCatMapper(TbItemCatMapper itemCatMapper) {
		this.itemCatMapper = itemCatMapper;
	}

}
