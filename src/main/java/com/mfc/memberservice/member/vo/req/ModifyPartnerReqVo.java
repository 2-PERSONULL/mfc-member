package com.mfc.memberservice.member.vo.req;

import lombok.Getter;

@Getter
public class ModifyPartnerReqVo {
	private String profileImage;
	private String description;
	private Integer startTime;
	private Integer endTime;
	private String bank;
	private String account;
	private Integer averageDate;
}
