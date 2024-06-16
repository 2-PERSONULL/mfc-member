package com.mfc.memberservice.member.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserInfoDto {
	private String userId;

}