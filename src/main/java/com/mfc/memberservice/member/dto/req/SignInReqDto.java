package com.mfc.memberservice.member.dto.req;

import com.mfc.memberservice.member.vo.req.SignInReqVo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInReqDto {
	private String email;
	private String password;
}
