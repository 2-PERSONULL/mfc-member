package com.mfc.memberservice.member.vo.req;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ModifyFavoriteStyleReqVo {
	private List<Long> favoriteStyles = new ArrayList<>();
}
