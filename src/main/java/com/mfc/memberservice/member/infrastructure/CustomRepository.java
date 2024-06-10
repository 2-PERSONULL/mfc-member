package com.mfc.memberservice.member.infrastructure;

import java.util.List;

import org.w3c.dom.stylesheets.LinkStyle;

import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;

public interface CustomRepository {
	ProfileRespDto getUserProfile(String uuid);
	ProfileRespDto getPartnerProfile(String uuid);
	List<FavoriteStyleDto> getFavoriteStyles(String uuid);
}
