package com.sy.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @deccription 文件上传
 *
 * @author zhanbiane
 * 2018年5月14日
 */
@RestController
public class uploadController {

	@PostMapping(value = "/upload")
	public void uploadFile(@RequestParam(name="file") MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		ImageInputStream im = ImageIO.createImageInputStream(file.getInputStream());
		Iterator<ImageReader> it = ImageIO.getImageReaders(im);
		//判断是否是图片
		if(it.hasNext()) {
//			response.setHeader("Content-Type","image/jped");
			response.getOutputStream().write(file.getBytes());
		}else {
			response.getWriter().write(file.getOriginalFilename());			
		}
		response.flushBuffer();
	}
	
	@PostMapping("/uploadfiles")
	public Object uploadFiles(HttpServletRequest request) {
		if(ServletFileUpload.isMultipartContent(request)) {
			List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
			for(MultipartFile file:files) {
				System.out.println(file.getOriginalFilename());
			}
			
			return "success";
		}
		return "type \"multipart/form-data\"";
	}
}
