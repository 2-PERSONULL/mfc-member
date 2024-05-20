package com.mfc.memberservice.member.dto.req;

import lombok.Getter;

@Getter
public class SignInReqDto {
	private String email;
	private String password;
}
