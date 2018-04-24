package cn.e3mall.test;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月23日 下午2:52:22
 */
public class ActiveMqQueuesTest {

	// 测试发送队列消息
	@Test
	public void testProducer() throws JMSException {
		// 1.创建连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 2.创建连接
		Connection connection = connectionFactory.createConnection();
		// 3.开启连接
		connection.start();
		// 4.创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建消息队列
		Queue queue = session.createQueue("测试消息队列");
		// 6.创建消息的生产者
		MessageProducer producer = session.createProducer(queue);
		// 7.创建字符串消息
		TextMessage message = session.createTextMessage("测试 ActiveMQ,使用消息队列");
		// 8.使用生产者发送消息
		producer.send(message);
		// 9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}

	// 测试接收消息队列
	@Test
	public void testConsumer() throws JMSException, IOException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 1.创建连接
		Connection connection = connectionFactory.createConnection();
		// 2.开启连接
		connection.start();
		// 3.创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建消息接收者
		Queue queue = session.createQueue("测试消息队列");
		// 5.创建接收者
		MessageConsumer consumer = session.createConsumer(queue);
		// 6.设置监听
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage tm = (TextMessage) message;
				// 打印消息
				try {
					System.out.println(tm.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		// 等待控制台输入
		System.in.read();
		// 7.关闭资源
		consumer.close();
		session.close();
		connection.close();

	}
	
	
	// 测试发送队列消息
		@Test
		public void testTopicProducer() throws JMSException {
			// 1.创建连接工厂
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
			// 2.创建连接
			Connection connection = connectionFactory.createConnection();
			// 3.开启连接
			connection.start();
			// 4.创建session对象
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 5.创建消息队列
			Topic topic = session.createTopic("测试广播传输消息");
			// 6.创建消息的生产者
			MessageProducer producer = session.createProducer(topic);
			// 7.创建字符串消息
			TextMessage message = session.createTextMessage("测试 ActiveMQ,使用广播");
			// 8.使用生产者发送消息
			producer.send(message);
			// 9.关闭资源
			producer.close();
			session.close();
			connection.close();
		}

		// 测试接收消息队列
		@Test
		public void testTopicConsumer() throws JMSException, IOException {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
			// 1.创建连接
			Connection connection = connectionFactory.createConnection();
			// 2.开启连接
			connection.start();
			// 3.创建session对象
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 4.创建消息接收者
			Topic topic = session.createTopic("测试广播传输消息");
			// 5.创建接收者
			MessageConsumer consumer = session.createConsumer(topic);
			// 6.设置监听
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage tm = (TextMessage) message;
					// 打印消息
					try {
						System.out.println(tm.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});

			// 等待控制台输入
			System.in.read();
			// 7.关闭资源
			consumer.close();
			session.close();
			connection.close();

		}
}
