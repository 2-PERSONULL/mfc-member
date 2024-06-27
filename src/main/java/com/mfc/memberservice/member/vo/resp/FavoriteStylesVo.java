package com.mfc.memberservice.member.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteStylesVo {
	private Long favoriteId;
	private Long styleId;
	private String name;
	private String partnerId;
}
