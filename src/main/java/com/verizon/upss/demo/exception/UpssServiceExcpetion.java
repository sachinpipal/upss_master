package com.verizon.upss.demo.exception;

public class UpssServiceExcpetion extends UpssBaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1764026745120600594L;

	public UpssServiceExcpetion(Exception ex, String message, String uesermessage, int errorCode, int userId) {
		super(ex, message, errorCode);
	}

}
