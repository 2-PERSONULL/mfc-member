package com.mfc.memberservice.member.dto.kafka;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ResponseMessage {
	private Long requestId;
	private String userId;
	private String partnerId;
	private LocalDate deadline;
	private String userImageUrl;
	private String userNickName;
	private Short userGender;
	private LocalDate userBirth;
}