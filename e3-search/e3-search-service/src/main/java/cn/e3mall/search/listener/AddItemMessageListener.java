package cn.e3mall.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import cn.e3mall.search.service.impl.SearchServiceImpl;

/**
 * 监听商品添加的消息
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月24日 上午11:00:08
*/
public class AddItemMessageListener implements MessageListener {

	@Autowired
	private SearchServiceImpl searchServiceImpl;
	
	public void onMessage(Message message) {
		//接收消息
		TextMessage textMessage = (TextMessage) message;
		try {
			String itemId = textMessage.getText();
			//将商品添加到索引库
			Long id = new Long(itemId);
			searchServiceImpl.addItemById(id);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
