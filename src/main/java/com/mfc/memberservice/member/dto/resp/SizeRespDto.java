package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SizeRespDto {
	private String topSize;
	private String bottomSize;
	private Integer shoeSize;
}
