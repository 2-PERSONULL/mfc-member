package com.mfc.memberservice.member.dto.req;

import java.util.List;

import lombok.Getter;

@Getter
public class PartnerRankingReqDto {
	private List<PartnerSummaryReqDto> partners;
}
