package com.tryall.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ReadUtil {
	public static void show(HttpServletRequest request, HttpServletResponse response, String noticeUrl)
			throws Exception {

		// response.setContentType("image/jpeg");
		FTPClient ftp = new FTPClient();
		int reply;
		ftp.connect("127.0.0.1", 21);
		// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
		ftp.login("admin", "123456");// 登录
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();

		}

		int len = 0;
		byte[] buff = new byte[1024];

		InputStream is = ftp.retrieveFileStream(noticeUrl); // 根据路径获得输入流，

		ServletOutputStream out = response.getOutputStream(); // 再将输入流输出
		while ((len = is.read(buff)) != -1) {
			out.write(buff, 0, len);
		}
		is.close();
		out.flush();
		out.close();
		ftp.logout();
	}

	/**
	 *
	 * 将一个字符串转化为输入流
	 */
	public static InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 *
	 * 将一个输入流转化为字符串
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
