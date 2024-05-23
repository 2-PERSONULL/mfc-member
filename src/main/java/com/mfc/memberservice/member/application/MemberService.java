package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.req.ModifyMemberReqDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;

public interface MemberService {
	ProfileRespDto getProfile(String uuid, String role);
	void modifyNickname(String uuid, String role, String nickname);
	void modifyPassword(String uuid, ModifyMemberReqDto dto);
	void modifyFavoriteStyle(String uuid, ModifyFavoriteStyleReqDto dto);
	void resign(String uuid);
}
