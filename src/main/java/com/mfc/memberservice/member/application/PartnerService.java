package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;

public interface PartnerService {
	void updateProfileImage(String uuid, ModifyPartnerReqDto dto);
}
