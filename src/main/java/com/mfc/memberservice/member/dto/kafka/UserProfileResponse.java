package com.mfc.memberservice.member.dto.kafka;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {
	private String userId;
	private String userImageUrl;
	private String userNickName;
}
