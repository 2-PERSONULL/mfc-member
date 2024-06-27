package com.mfc.memberservice.member.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartnerRankingDto {
	private String partnerId;
	private String nickname;
	private String profileImage;
	private String alt;
	private Integer followerCnt;
	private Integer coordinateCnt;
	private Double averageStar;
	private List<StyleValueDto> styles;
}
