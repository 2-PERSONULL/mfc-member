package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartnerAccountRespDto {
	private String bank;
	private String account;
}
