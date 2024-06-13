package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileRespDto {
	private String nickname;
	private String profileImage;
	private String imageAlt;
}
