package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * easyui中树的节点
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月18日 上午10:32:41
*/
public class TreeNodeResult implements Serializable {
	 /**
	  * 当前节点ID
	  */
	private Long id;        
	/**
	 * 当前节点name
	 */
	private String text;     
	/**
	 * 当前节点状态 closed open
	 */
	private String state;    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
