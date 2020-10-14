package com.atguigu.crowd.exception;

/**
 * @author 作者 陈思必
 * @version 创建时间：2020年10月13日 上午11:41:41
 * 
 */
public class LoginFailedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LoginFailedException() {
		super();
	}

	public LoginFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailedException(String message) {
		super(message);
	}

	public LoginFailedException(Throwable cause) {
		super(cause);
	}

}
