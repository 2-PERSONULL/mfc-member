package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;

public interface UserService {
	void updateProfile(String uuid, ModifyUserReqDto dto);
}
