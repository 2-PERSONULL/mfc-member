package com.mfc.memberservice.member.application;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.mfc.memberservice.member.dto.req.CareerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.UpdateSnsReqDto;
import com.mfc.memberservice.member.dto.resp.CareerListRespDto;
import com.mfc.memberservice.member.dto.resp.PartnerAccountRespDto;
import com.mfc.memberservice.member.dto.resp.PartnerPortfolioRespDto;
import com.mfc.memberservice.member.dto.resp.PartnerProfileListRespDto;
import com.mfc.memberservice.member.dto.resp.PartnerRankingRespDto;
import com.mfc.memberservice.member.dto.resp.PartnersByStyleRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.dto.resp.SnsListRespDto;

public interface PartnerService {
	void updateProfileImage(String uuid, ModifyPartnerReqDto dto);
	void updateSns(String uuid, UpdateSnsReqDto dto);
	SnsListRespDto getSnsList(String partnerId);
	void createCareer(String uuid, CareerReqDto dto);
	void updateCareer(String uuid, Long careerId, CareerReqDto dto);
	void deleteCareer(String uuid, Long careerId);
	CareerListRespDto getCareerList(String partnerId);
	void updateDescription(String uuid, ModifyPartnerReqDto dto);
	void updateAccount(String uuid, ModifyPartnerReqDto dto);
	void updateAverageTime(String uuid, ModifyPartnerReqDto dto);
	void updateChatTime(String uuid, ModifyPartnerReqDto dto);
	void updateAveragePrice(String uuid, ModifyPartnerReqDto dto);
	PartnerPortfolioRespDto getPortfolio(String partnerId);
	PartnerAccountRespDto getAccount(String partnerId);
	ProfileRespDto getProfile(String partnerId);
	PartnersByStyleRespDto getPartnersByStyle(Long styleId);
	PartnerProfileListRespDto getPartnerProfiles(List<String> partnerIds);
	PartnersByStyleRespDto getPartnersByStyles(String uuid);
	PartnerRankingRespDto getPartnerRanking();
}
