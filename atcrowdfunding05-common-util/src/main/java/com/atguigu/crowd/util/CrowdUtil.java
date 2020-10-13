package com.atguigu.crowd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CrowdUtil {

	/**
	 * 判断当前请求是否为Ajax请求
	 * 
	 * @param request
	 *            请求对象
	 * @return true：当前请求是Ajax请求 false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {

		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");

		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))

				||

				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}

	/**
	 * md5加密
	 * 
	 * @return
	 */

	public static String md5(String passWord) {
		String encoded = null;
		try {
			if (passWord == null || passWord.length() == 0) {
				throw new RuntimeException("密码不存在！");
			}
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			byte[] bytes = passWord.getBytes();
			byte[] digest = messageDigest.digest(bytes);
			BigInteger bigInteger = new BigInteger(1, digest);
			encoded = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encoded;
	}

}
