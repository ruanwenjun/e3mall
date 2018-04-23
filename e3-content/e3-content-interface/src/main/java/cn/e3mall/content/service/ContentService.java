package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContent;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月20日 下午8:00:24
*/
public interface ContentService {

	//分页查找内容
	DataGridResult pageQuery(Long categoryId, int page, int rows);

	//更新内容
	E3Result updateContent(TbContent content);
	
	//新增内容
	E3Result addContent(TbContent content);
	
	//根据内容分类ID查找内容
	List<TbContent> selectContentListByCategoryId(Long aD1_CATEGORY_ID);

}
