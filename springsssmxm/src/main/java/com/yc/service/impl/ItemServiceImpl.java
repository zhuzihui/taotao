package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.PageHelperData;
import com.yc.bean.TbItem;
import com.yc.bean.TbItemExample;
import com.yc.bean.TbItemExample.Criteria;
import com.yc.mapper.TbItemMapper;
import com.yc.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired//有2个或多个继承这个实例化对象的类的话测试就会报错，它只能单独存在
	private TbItemMapper tbItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemid) {
		
		//TbItem item = tbItemMapper.selectByPrimaryKey(itemid);//直接查询
		
		TbItemExample example = new TbItemExample();	//根据查询条件查询
		//添加查询条件
		Criteria criteria = example.createCriteria();	//criteria条件 创建条件
		criteria.andIdEqualTo(itemid);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;

	}
	//分页查询商品
	@Override
	public PageHelperData getItemList(int  page, int rows) {
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list=tbItemMapper.selectByExample(example);
		//创建一个返回值
		PageHelperData result=new PageHelperData();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		System.out.println(result);
		return result;
	}
	
	public void setTbItemMapper(TbItemMapper tbItemMapper) {
		this.tbItemMapper = tbItemMapper;
	}
	@Override
	public int save(TbItem tbItem) {
		return tbItemMapper.insert(tbItem);
		
	}
	
	

}
