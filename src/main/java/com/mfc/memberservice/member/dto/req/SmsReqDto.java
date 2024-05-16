package com.mfc.memberservice.member.dto.req;

import com.mfc.memberservice.member.vo.req.SmsReqVo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SmsReqDto {
	private String phone;
	private String code;
}
