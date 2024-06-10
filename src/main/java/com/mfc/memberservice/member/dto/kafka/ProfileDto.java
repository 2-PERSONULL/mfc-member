package com.mfc.memberservice.member.dto.kafka;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileDto {
	private String uuid;
	private String nickname;
	private List<Long> favoriteStyles;
	private String role;
}
