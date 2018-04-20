package cn.e3mall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.common.utils.FastDFSClient;
import cn.common.utils.JsonUtils;

/**
 * 图片controller
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月19日 下午3:20:26
 */
@Controller
public class PictureController {

	@Value(value = "${IP_NAME}")
	private String IP_NAME;

	// 上传图片
	@RequestMapping("/pic/upload")
	public @ResponseBody String uploadPicture(MultipartFile uploadFile) {
		Map map = new HashMap<>();
		try {
			// 获得文件字节数组
			byte[] bytes = uploadFile.getBytes();
			String name = uploadFile.getOriginalFilename();
			// 获得扩展名
			String extName = FilenameUtils.getExtension(name);
			// 上传图片
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			String url = fastDFSClient.uploadFile(bytes, extName);

			// 上传成功,将url拼装
			url = IP_NAME + "/" + url;
			map.put("error", 0);
			map.put("url", url);
		} catch (Exception e) {
			// 上传失败
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "上传失败");
		}
		return JsonUtils.objectToJson(map);
	}
}
