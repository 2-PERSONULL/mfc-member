package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.SignInReqDto;
import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.dto.req.SmsReqDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;
import com.mfc.memberservice.member.vo.req.SignInReqVo;
import com.mfc.memberservice.member.vo.req.SignUpReqVo;

public interface AuthService {
	void signUp(SignUpReqDto dto); // 회원가입
	void sendSms(SmsReqDto dto); // 문자 전송
	void verifyCode(SmsReqDto dto); // 문자 검증
	boolean verifyNickname(String nickname, String role); // 닉네임 중복 검증
	SignInRespDto signIn(SignInReqDto dto);

}
