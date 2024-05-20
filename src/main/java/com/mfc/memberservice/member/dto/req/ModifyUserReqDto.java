package com.mfc.memberservice.member.dto.req;

import lombok.Getter;

@Getter
public class ModifyUserReqDto {
	private String nickname;
	private String profileImage;
	private String imageAlt;
	private Integer height;
	private Integer weight;
	private String topSize;
	private String bottomSize;
	private Integer shoeSize;
	private String bodyType;
}
