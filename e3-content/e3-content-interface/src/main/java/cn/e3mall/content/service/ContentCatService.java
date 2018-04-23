package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodeResult;

/**
 * 首页内容分类Service
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月20日 下午12:48:45
*/
public interface ContentCatService {

	// 获得首页内容分类的列表树
	List<TreeNodeResult> getContentCategoryList(Long parentId);
	
	// 新增首页内容分类的列表树的节点
	E3Result addContentCategory(Long parentId,String name);
	
	// 删除分类
	void deleteContentCategory(Long id);

	void updateContentCategory(Long id, String name);
}
