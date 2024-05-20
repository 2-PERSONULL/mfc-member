package com.mfc.memberservice.member.infrastructure;

import com.mfc.memberservice.member.dto.resp.ProfileRespDto;

public interface CustomMemberRepository {
	ProfileRespDto getUserProfile(String uuid);
	ProfileRespDto getPartnerProfile(String uuid);
}
