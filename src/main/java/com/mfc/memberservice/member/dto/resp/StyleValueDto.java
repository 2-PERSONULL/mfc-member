package com.mfc.memberservice.member.dto.resp;

import lombok.Getter;

@Getter
public class StyleValueDto {
	private Long styleId;
	private String name;

	public StyleValueDto(FavoriteStylesDto dto) {
		this.styleId = dto.getStyleId();
		this.name = dto.getName();
	}
}
