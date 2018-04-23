package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.common.pojo.TreeNodeResult;
import cn.e3mall.dao.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.service.ItemCatService;

/**
 * 商品列表服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月18日 上午10:37:29
*/
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper mapper;
	//根据父节点ID查询子节点列表
	@Override
	public List<TreeNodeResult> getItemCatList(long parentId) {
		//根据父节点id查询目录列表
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> ItemList = mapper.selectByExample(example);
		//封装返回的树节点列表
		List<TreeNodeResult> result = new ArrayList<TreeNodeResult>();
		for (TbItemCat itemCat : ItemList) {
			TreeNodeResult treeNode = new TreeNodeResult();
			treeNode.setId(itemCat.getId());
			treeNode.setText(itemCat.getName());
			treeNode.setState(itemCat.getIsParent()?"closed":"open");
			result.add(treeNode);
		}
		return result;
	}

}
