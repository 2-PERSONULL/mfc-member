package com.mfc.memberservice.member.dto.resp;

import org.springframework.web.bind.annotation.PostMapping;

import com.mfc.memberservice.member.domain.Partner;

import lombok.Getter;

@Getter
public class ProfilesDto {
	private String partnerId;
	private String nickname;
	private String profileImage;
	private String imageAlt;

	public ProfilesDto(Partner partner) {
		this.partnerId = partner.getUuid();
		this.nickname = partner.getNickname();
		this.profileImage = partner.getProfileImage();
		this.imageAlt = partner.getImageAlt();
	}
}
