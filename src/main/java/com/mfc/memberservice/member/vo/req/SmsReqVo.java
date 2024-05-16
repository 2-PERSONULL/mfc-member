package com.mfc.memberservice.member.vo.req;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SmsReqVo {
	private String phone;
	private String code;
}
