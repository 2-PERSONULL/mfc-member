package com.mfc.memberservice.member.infrastructure;

import java.util.List;

import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStylesDto;

public interface CustomRepository {
	List<FavoriteStyleDto> getFavoriteStyles(String uuid);
	List<FavoriteStylesDto> getFavoriteStyles(List<String> partnerIds);
}
