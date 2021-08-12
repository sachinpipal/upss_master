package com.verizon.upss.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpssBaseException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1206332862180836467L;
	private final Exception ex;
	private final String message;
	private final int errorCode;

}
