package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;

public interface MemberService {
	boolean verifyNickname(String nickname); // 닉네임 중복 검증
	void modifyNickname(String uuid, String role, String nickname);
	void modifyFavoriteStyle(String uuid, ModifyFavoriteStyleReqDto dto);
	FavoriteStyleRespDto getFavoriteStyle(String uuid);
	SignInRespDto changeRole(String uuid, String role);
}
