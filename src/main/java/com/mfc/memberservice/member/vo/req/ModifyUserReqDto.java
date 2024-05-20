package com.mfc.memberservice.member.vo.req;

import lombok.Getter;

@Getter
public class ModifyUserReqDto {
	private String nickname;
	private String profileImage;
	private String imageAlt;
	private Integer height;
	private String topSize;
	private String bottomSize;
	private Integer shoeSize;
}
