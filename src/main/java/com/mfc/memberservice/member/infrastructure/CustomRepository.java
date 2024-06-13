package com.mfc.memberservice.member.infrastructure;

import java.util.List;

import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;

public interface CustomRepository {
	List<FavoriteStyleDto> getFavoriteStyles(String uuid);
}
