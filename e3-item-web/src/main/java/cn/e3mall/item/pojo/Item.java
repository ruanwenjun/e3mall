package cn.e3mall.item.pojo;

import cn.e3mall.pojo.TbItem;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月24日 下午2:57:11
 */
public class Item extends TbItem {

	public Item(TbItem item) {
		this.setId(item.getId());
		this.setTitle(item.getTitle());
		this.setSellPoint(item.getSellPoint());
		this.setPrice(item.getPrice());
		this.setNum(item.getNum());
		this.setBarcode(item.getBarcode());
		this.setImage(item.getImage());
		this.setCid(item.getCid());
		this.setStatus(item.getStatus());
		this.setCreated(item.getCreated());
		this.setUpdated(item.getUpdated());
	}

	public String[] getImages() {
		String image = this.getImage();
		if (image != null && !image.equals("")) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
}
