package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 前台查询商品的Dao结果
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午8:27:31
*/
public class SearchResult implements Serializable{
	//总数
	private Long recourdCount;   
	//商品查询到的列表
	private List<SearchItem> itemList;  
	//总页数
	private int totalPages;             
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Long getRecourdCount() {
		return recourdCount;
	}
	public void setRecourdCount(Long recourdCount) {
		this.recourdCount = recourdCount;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
}
