package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRespDto {
	private String uuid;
	private String accessToken;
	private String refreshToken;
	private String role;
}
