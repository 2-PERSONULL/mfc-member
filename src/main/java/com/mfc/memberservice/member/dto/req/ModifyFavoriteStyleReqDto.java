package com.mfc.memberservice.member.dto.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ModifyFavoriteStyleReqDto {
	private List<Long> favoriteStyles = new ArrayList<>();
}
