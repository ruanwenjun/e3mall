package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNodeResult;
import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.dao.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月20日 下午12:55:48
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper categoryMapper;

	// 得到内容分类类别列表
	public List<TreeNodeResult> getContentCategoryList(Long parentId) {
		// 根据ID查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = categoryMapper.selectByExample(example);
		// 遍历查询结果列表，封装返回的结果集
		List<TreeNodeResult> result = new ArrayList<TreeNodeResult>();
		for (TbContentCategory tbContentCategory : list) {
			TreeNodeResult node = new TreeNodeResult();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			result.add(node);
		}
		return result;
	}

	// 在父节点下新增分类
	public E3Result addContentCategory(Long parentId, String name) {
		// 创建节点
		TbContentCategory category = new TbContentCategory();
		// 为节点封装数据
		category.setName(name);
		category.setParentId(parentId);
		// 排序，默认为1
		category.setSortOrder(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		// 状态 可选值:1(正常),2(删除)
		category.setStatus(1);
		// 是否为父节点
		category.setIsParent(false);
		// 导入数据库
		categoryMapper.insert(category);
		
		//修改父节点的信息
		TbContentCategory parent = categoryMapper.selectByPrimaryKey(category.getParentId());
		if(!parent.getIsParent()) {
			parent.setIsParent(true);
			categoryMapper.updateByPrimaryKeySelective(parent);
		}
		return E3Result.ok(category);
	}
	
	//删除指定ID的节点，并且删除它的子节点
	public void deleteContentCategory(Long id) {
		// 先从数据库查询被删除节点
		TbContentCategory node = categoryMapper.selectByPrimaryKey(id);
		
		// 查询被删除节点是否有兄弟，如果没有兄弟，则修改其父节点的状态。
		if(node.getParentId() != 0) {
			TbContentCategoryExample parentExample = new TbContentCategoryExample();
			parentExample.createCriteria().andParentIdEqualTo(node.getParentId());
			List<TbContentCategory> brothers = categoryMapper.selectByExample(parentExample);
			if(brothers.size() == 1) {
				//单亲没有兄弟，修改父节点状态
				TbContentCategory parent = categoryMapper.selectByPrimaryKey(node.getParentId());
				parent.setIsParent(false);
				parent.setUpdated(new Date());
				categoryMapper.updateByPrimaryKeySelective(parent);
			}
		}
		
		//如果被删除节点是父节点,则递归删除所有子节点
		if(node.getIsParent()) {
			TbContentCategoryExample childExample = new TbContentCategoryExample();
			childExample.createCriteria().andParentIdEqualTo(id);
			List<TbContentCategory> childs = categoryMapper.selectByExample(childExample);
			// 遍历子节点，并且递归删除子节点
			for (TbContentCategory child : childs) {
				deleteContentCategory(child.getId());
			}
		}
		
		// 最后删除该节点
		categoryMapper.deleteByPrimaryKey(id);
		
	}
	
	//修改节点内容
	public void updateContentCategory(Long id, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setId(id);
		category.setName(name);
		category.setUpdated(new Date());
		categoryMapper.updateByPrimaryKeySelective(category);
	}

}
