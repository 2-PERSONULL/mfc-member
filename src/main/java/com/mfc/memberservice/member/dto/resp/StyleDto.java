package com.mfc.memberservice.member.dto.resp;

import com.mfc.memberservice.member.domain.Style;

import lombok.Getter;

@Getter
public class StyleDto {
	private Long styleId;
	private String name;
	private String imageUrl;
	private String alt;

	public StyleDto(Style style) {
		this.styleId = style.getId();
		this.name = style.getName();
		this.imageUrl = style.getImageUrl();
		this.alt = style.getAlt();
	}
}
