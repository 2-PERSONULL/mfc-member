package com.mfc.memberservice.member.dto.req;

import lombok.Getter;

@Getter
public class SmsReqDto {
	private String phone;
	private String code;
}
