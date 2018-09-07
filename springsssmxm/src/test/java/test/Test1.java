package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.bean.PageHelperData;
import com.yc.bean.TbItem;
import com.yc.bean.TbItemCat;
import com.yc.bean.TbItemExample;
import com.yc.bean.TbItemCatExample.Criteria;
import com.yc.mapper.TbItemCatMapper;
import com.yc.mapper.TbItemMapper;
import com.yc.service.ItemCatService;
import com.yc.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)//这种写法是为了让测试在Spring容器环境下执行
@ContextConfiguration(locations={"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml","classpath:applicationContext-trans.xml"})	
public class Test1 {
	@Autowired
	private ItemService itemService;

	@Autowired
	private TbItemMapper ibItemMapper;

	@Autowired
	private ItemCatService itemCatService;


	@Test
	public void test() {
		System.out.println("测试框架ok");
	}

	@Test	//测试查询
	public void test2() {
		TbItem tbitem=itemService.getItemById(536563);
		System.out.println(tbitem);
	}
	@Test	//测试分页查询
	public void test3() {
		//执行查询，并分页
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(1, 10);
		List<TbItem> list = ibItemMapper.selectByExample(example);
		System.out.println(list);
		//取商品列表
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品："+ total);
	}
	@Test	//测试查询
	public void test4() {
		PageHelperData tbitem=itemService.getItemList(1, 20);
		System.out.println(tbitem);
	}

	@Test	//类目查询测试
	public void test5() {
		List<TbItemCat> list=itemCatService.getItemCatList(2l);
		System.out.println(list+"00000000000000000000000000000000000000000000000");
	}


}
