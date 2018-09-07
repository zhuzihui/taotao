package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.TbContentCategory;
import com.yc.bean.TbContentCategoryExample;
import com.yc.bean.TbContentCategoryExample.Criteria;
import com.yc.mapper.TbContentCategoryMapper;
import com.yc.service.ContentCatService;


@Service
public class ContentCatServiceImpl implements ContentCatService {
	
	@Autowired
	private TbContentCategoryMapper tbcontenMapper;
	
	@Override
	public List<TbContentCategory> getContentCatList(Long parentId) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> contentList=tbcontenMapper.selectByExample(example);
		return contentList;
	}

	@Override
	public long insertCat(TbContentCategory tbContentCategory) {
		//添加并返回id
		long id=tbcontenMapper.insert(tbContentCategory);
		//查看父节点的isparent是否为true，若不是则改为true
		TbContentCategory tb=tbcontenMapper.selectByPrimaryKey(tbContentCategory.getParentId());
		boolean b=tb.getIsParent();
		if(b==false){
			tb.setIsParent(true);
			tbcontenMapper.updateByPrimaryKey(tb);
		}
		return id;
		
	}

	@Override
	public void deleteCat(long id) {
		tbcontenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void renameCat(long id, String name) {
		TbContentCategory tbContentCategory=new TbContentCategory();
		tbContentCategory.setName(name);
		
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(id);
		tbcontenMapper.updateByExampleSelective(tbContentCategory, example);
	}

}
