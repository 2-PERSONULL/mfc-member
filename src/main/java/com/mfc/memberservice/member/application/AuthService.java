package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.vo.req.SignUpReqVo;

public interface AuthService {
	void signUp(SignUpReqDto dto, String role);
}
