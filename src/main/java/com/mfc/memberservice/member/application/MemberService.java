package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.resp.ProfileRespDto;

public interface MemberService {
	ProfileRespDto getProfile(String uuid, String role);
}
