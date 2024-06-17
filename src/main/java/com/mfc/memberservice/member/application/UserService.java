package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.resp.UserProfileResponse;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.dto.resp.BodyTypeRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.dto.resp.SizeRespDto;

public interface UserService {
	void updateProfile(String uuid, ModifyUserReqDto dto);
	void updateSize(String uuid, ModifyUserReqDto dto);
	void updateProfileImage(String uuid, ModifyUserReqDto dto);
	void updateBodyType(String uuid, ModifyUserReqDto dto);
	ProfileRespDto getProfile(String userId);
	BodyTypeRespDto getBodyType(String userId);
	SizeRespDto getSize(String userId);

	UserProfileResponse getNicknameImage(String userId);
}
