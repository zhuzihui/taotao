package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yc.bean.TbContent;
import com.yc.bean.TbContentExample;
import com.yc.bean.TbContentExample.Criteria;
import com.yc.mapper.TbContentMapper;
import com.yc.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	
	@Override
	public List<TbContent> getContentList() {
		TbContentExample example=new TbContentExample();
		List<TbContent> listContent=tbContentMapper.selectByExample(example);
		return listContent;
	}


	@Override
	public void addContent(TbContent tbContent) {
		tbContentMapper.insert(tbContent);
	}


	@Override
	public void updataContent(TbContent tbContent) {
		TbContentExample example=new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdEqualTo(tbContent.getId());
		tbContentMapper.updateByExampleSelective(tbContent, example);
	}


	@Override
	public void deleteContent(long id) {
		tbContentMapper.deleteByPrimaryKey(id);
	}

}
