package com.mfc.memberservice.member.dto.resp;

import com.mfc.memberservice.member.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileRespDto {
	private String profileImage;
	private String imageAlt;
	private String nickname;
	private String email;

	public static ProfileRespDto toUser(User user) {
		return ProfileRespDto.builder()
				.profileImage(user.getProfileImage())
				.imageAlt(user.getImageAlt())
				.email(user.getUuid())
				.nickname(user.getNickname())
				.build();
	}
}
