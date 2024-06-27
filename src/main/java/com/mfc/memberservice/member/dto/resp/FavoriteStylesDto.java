package com.mfc.memberservice.member.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteStylesDto {
	private Long favoriteId;
	private Long styleId;
	private String name;
	private String partnerId;
}
