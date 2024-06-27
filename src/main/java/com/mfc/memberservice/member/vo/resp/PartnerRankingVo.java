package com.mfc.memberservice.member.vo.resp;

import java.util.List;

import com.mfc.memberservice.member.dto.resp.StyleDto;
import com.mfc.memberservice.member.dto.resp.StyleValueDto;

import lombok.Getter;

@Getter
public class PartnerRankingVo {
	private String partnerId;
	private String nickname;
	private String profileImage;
	private String alt;
	private Integer followerCnt;
	private Integer coordinateCnt;
	private Double averageStar;
	private List<StyleValueVo> styles;
}
