package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.common.pojo.DataGridResult;
import cn.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.dao.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;

/**
 * 首页显示内容服务
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月20日 下午8:33:30
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper tbContentMapper;

	// 分页查找
	public DataGridResult pageQuery(Long categoryId, int page, int rows) {
		// 分页查询插件
		PageHelper helper = new PageHelper();
		helper.startPage(page, rows);

		// 设置查询条件
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(example);

		PageInfo<TbContent> info = new PageInfo<>(list);
		Long total = info.getTotal();

		// 封装结果
		DataGridResult result = new DataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	// 更新内容
	public E3Result updateContent(TbContent content) {
		// 更新内容
		content.setUpdated(new Date());
		tbContentMapper.updateByPrimaryKey(content);
		
		return E3Result.ok();
	}
	
	//新增内容
	public E3Result addContent(TbContent content) {
		content.setUpdated(new Date());
		tbContentMapper.insert(content);
		return E3Result.ok();
	}
	
	//根据分类ID查找内容
	public List<TbContent> selectContentListByCategoryId(Long aD1_CATEGORY_ID) {
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(aD1_CATEGORY_ID);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		return list;
	}

}
