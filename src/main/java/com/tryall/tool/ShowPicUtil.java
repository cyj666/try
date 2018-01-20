package com.tryall.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;



/**
 * 从ftp返回图片，以流的方式
 * 
 * @author DELL
 *
 */
public class ShowPicUtil {

	public static void show(HttpServletRequest request, HttpServletResponse response,String picUrl)
			throws Exception {
		
		response.setContentType("image/jpeg");		
		FTPClient ftp = new FTPClient();		
		int reply;
		ftp.connect("127.0.0.1", 21);
		// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
		ftp.login("admin", "123456");// 登录
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			
		}
		
		
		int len=0;
		byte [] buff=new byte[1024];
		
		InputStream is = ftp.retrieveFileStream(picUrl); //根据路径获得输入流，
		
		ServletOutputStream out = response.getOutputStream();  //再将输入流输出
		while((len=is.read(buff))!=-1){
			out.write(buff, 0, len);
			}
			is.close();
			out.flush();
			out.close();
	}
}
