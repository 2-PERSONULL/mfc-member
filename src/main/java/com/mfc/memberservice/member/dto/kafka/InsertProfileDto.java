package com.mfc.memberservice.member.dto.kafka;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertProfileDto {
	private String uuid;
	private String nickname;
	private List<Long> favoriteStyles;
	private String role;
}
