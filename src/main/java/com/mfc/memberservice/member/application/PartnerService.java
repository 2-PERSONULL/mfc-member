package com.mfc.memberservice.member.application;

import com.mfc.memberservice.member.dto.req.CareerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.UpdateSnsReqDto;
import com.mfc.memberservice.member.dto.resp.CareerListRespDto;
import com.mfc.memberservice.member.dto.resp.SnsListRespDto;

public interface PartnerService {
	void updateProfileImage(String uuid, ModifyPartnerReqDto dto);
	void updateSns(String uuid, UpdateSnsReqDto dto);
	SnsListRespDto getSnsList(String partnerId);
	void createCareer(String uuid, CareerReqDto dto);
	void updateCareer(String uuid, Long careerId, CareerReqDto dto);
	void deleteCareer(String uuid, Long careerId);
	CareerListRespDto getCareerList(String partnerId);
}
