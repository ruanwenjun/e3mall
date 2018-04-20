import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月19日 下午2:02:52
*/
public class Demo {
	
	@Test
	public void test() throws FileNotFoundException, IOException, MyException {
		//加载配置文件
		ClientGlobal.init("E:\\ecplices _workspace\\simplework\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
		//创建客户端
		TrackerClient client = new TrackerClient();
		//获得连接
		TrackerServer trackerServer = client.getConnection();
		StorageServer storageServer = null;
		
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//上传文件
		String[] files = storageClient.upload_file("E:\\照片\\IMG_20170514_115256.jpg", "jpg", null);
		
		for (String string : files) {
			System.out.println(string);
		}
	}
}
