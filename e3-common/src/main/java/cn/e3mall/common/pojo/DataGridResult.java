package cn.e3mall.common.pojo;


import java.io.Serializable;
import java.util.List;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月17日 下午9:50:19
*/
public class DataGridResult implements Serializable{
	
	private long total;
	private List rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(Long total2) {
		this.total = total2;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}

}
