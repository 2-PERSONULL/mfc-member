package com.mfc.memberservice.member.dto.kafka;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestMessage {
	private Long requestId;
	private String userId;
	private String partnerId;
	private LocalDate deadline;
	// getters and setters
}