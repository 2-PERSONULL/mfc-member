package com.mfc.memberservice.member.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteStyleDto {
	private Long favoriteId;
	private Long styleId;
	private String name;
}
