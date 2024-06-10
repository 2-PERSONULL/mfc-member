package com.mfc.memberservice.member.dto.resp;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StyleListRespDto {
	List<StyleDto> styles;
}
