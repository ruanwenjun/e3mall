package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * 索引库查询商品时的POJO
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午3:20:08
*/
public class SearchItem implements Serializable{
	
	private String id;
	private String title;
	private String image;
	private String sellPoint;
	private String categoryName;
	private Long price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
}
