package com.mfc.memberservice.common.exception;

import com.mfc.memberservice.common.response.BaseResponseStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
	private BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}

}
