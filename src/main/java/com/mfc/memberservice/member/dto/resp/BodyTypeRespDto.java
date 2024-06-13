package com.mfc.memberservice.member.dto.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BodyTypeRespDto {
	private Integer height;
	private Integer weight;
	private String bodyType;
}
