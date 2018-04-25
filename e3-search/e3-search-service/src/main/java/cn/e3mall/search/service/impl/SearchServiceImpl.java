package cn.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchService;

/**
 * 
 * 后台商品添加到索引库service
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月22日 下午3:36:49
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private ItemMapper mapper;
	@Autowired
	private SolrServer solrServer;

	// 将所有商品添加到索引库
	public E3Result addAllToSolr() {
		try {
			// 从数据库中查询所有商品
			List<SearchItem> items = mapper.selectAllItem();
			// 遍历所有商品，并添加到索引库
			for (SearchItem item : items) {
				SolrInputDocument doc = new SolrInputDocument();
				// 封装文档对象
				doc.addField("id", item.getId());
				doc.addField("item_title", item.getTitle());
				doc.addField("item_sell_point", item.getSellPoint());
				doc.addField("item_price", item.getPrice());
				doc.addField("item_image", item.getImage());
				doc.addField("item_category_name", item.getCategoryName());
				solrServer.add(doc);
			}
			solrServer.commit();
			return E3Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "添加索引失败");
		}
	}

	// 将指定ID的商品添加到索引库
	public void addItemById(Long id) {
		try {
			// 从数据库中查询所有商品
			SearchItem item = mapper.selectItemById(id);
			//创建文档对象
			SolrInputDocument doc = new SolrInputDocument();
			// 封装文档对象
			doc.addField("id", item.getId());
			doc.addField("item_title", item.getTitle());
			doc.addField("item_sell_point", item.getSellPoint());
			doc.addField("item_price", item.getPrice());
			doc.addField("item_image", item.getImage());
			doc.addField("item_category_name", item.getCategoryName());
			solrServer.add(doc);
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
