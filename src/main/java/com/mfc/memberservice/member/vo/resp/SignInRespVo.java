package com.mfc.memberservice.member.vo.resp;

import lombok.Getter;

@Getter
public class SignInRespVo {
	private String uuid;
	private String accessToken;
	private String refreshToken;
	private String role;
}
