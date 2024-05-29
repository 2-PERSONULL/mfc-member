package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartnerPortfolioRespDto {
	private String description;
	private Integer startTime;
	private Integer endTime;
	private Integer averageDate;
}
