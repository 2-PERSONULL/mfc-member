package com.mfc.memberservice.member.dto.req;

import java.time.LocalTime;

import lombok.Getter;

@Getter
public class ModifyPartnerReqDto {
	private String profileImage;
	private String description;
	private Integer startTime;
	private Integer endTime;
	private String bank;
	private String account;
	private Integer averageDate;
}
