package com.bootdo.common.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.R;

import main.java.com.UpYun;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/upload")
public class FileController extends BaseController {


	@ResponseBody
	@RequestMapping("/upyun")
	public R list(@RequestParam("file") MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			String uuid = UUID.randomUUID().toString();
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
			String wjjName = DateFormatUtils.format(new Date(), "yyyyMMdd");
			UpYun upyun = new UpYun("image-huazheng", "wenyahui", "wenyahui123");
			upyun.setDebug(true);
			upyun.setTimeout(60);
			String uploadFileName = "/"+wjjName+"/"+uuid+"."+suffix;
			boolean flag = upyun.writeFile(uploadFileName, file.getBytes());
			if(flag) {
				return new R(flag, "http://img.roohomes.com"+uploadFileName);
			}else {
				return new R(flag, "");
			}
		}
		return new R(false, "");
	}


}
