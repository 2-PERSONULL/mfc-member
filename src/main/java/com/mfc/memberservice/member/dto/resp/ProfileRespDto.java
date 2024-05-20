package com.mfc.memberservice.member.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileRespDto {
	private String profileImage;
	private String imageAlt;
	private String nickname;
	private String email;
}
