package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.req.ModifyMemberReqDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;

public interface MemberService {
	void modifyNickname(String uuid, String role, String nickname);
	void modifyPassword(String uuid, ModifyMemberReqDto dto);
	void modifyFavoriteStyle(String uuid, ModifyFavoriteStyleReqDto dto);
	FavoriteStyleRespDto getFavoriteStyle(String uuid);
	void resign(String uuid);
	SignInRespDto changeRole(String uuid, String role);
}
