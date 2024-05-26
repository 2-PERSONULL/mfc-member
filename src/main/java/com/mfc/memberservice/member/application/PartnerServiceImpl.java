package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.Career;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.Sns;
import com.mfc.memberservice.member.dto.req.CareerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.SnsDto;
import com.mfc.memberservice.member.dto.req.UpdateSnsReqDto;
import com.mfc.memberservice.member.dto.resp.CareerDto;
import com.mfc.memberservice.member.dto.resp.CareerListRespDto;
import com.mfc.memberservice.member.dto.resp.SnsListRespDto;
import com.mfc.memberservice.member.infrastructure.CareerRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.SnsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerServiceImpl implements PartnerService {
	private final PartnerRepository partnerRepository;
	private final SnsRepository snsRepository;
	private final CareerRepository careerRepository;

	@Override
	public void updateProfileImage(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(dto.getProfileImage()) // 수정
				.nickname(partner.getNickname())
				.imageAlt("Profile Image")
				.description(partner.getDescription())
				.account(partner.getAccount())
				.average_date(partner.getAverage_date())
				.build());
	}

	@Override
	public void updateSns(String uuid, UpdateSnsReqDto dto) {
		snsRepository.deleteByPartnerId(uuid);
		dto.getSns()
				.forEach(sns -> snsRepository.save(Sns.builder()
						.type(sns.getType())
						.partnerId(uuid)
						.snsUrl(sns.getSnsUrl())
						.build())
				);
	}

	@Override
	@Transactional(readOnly = true)
	public SnsListRespDto getSnsList(String partnerId) {
		return SnsListRespDto.builder()
				.sns(snsRepository.findByPartnerId(partnerId)
						.stream()
						.map(SnsDto::new)
						.toList())
				.build();
	}

	@Override
	public void createCareer(String uuid, CareerReqDto dto) {
		isExist(uuid);
		careerRepository.save(Career.builder()
				.partnerId(uuid)
				.title(dto.getTitle())
				.description(dto.getDescription())
				.startDate(dto.getStartDate())
				.finishDate(dto.getFinishDate())
				.build()
		);
	}

	@Override
	public void updateCareer(String uuid, Long careerId, CareerReqDto dto) {
		careerRepository.findByIdAndPartnerId(careerId, uuid)
				.orElseThrow(() -> new BaseException(CAREER_NOT_FOUND));

		careerRepository.save(Career.builder()
				.id(careerId)
				.title(dto.getTitle())
				.description(dto.getDescription())
				.startDate(dto.getStartDate())
				.finishDate(dto.getFinishDate())
				.build()
		);
	}

	@Override
	public void deleteCareer(String uuid, Long careerId) {
		careerRepository.deleteByIdAndPartnerId(careerId, uuid);
	}

	@Override
	@Transactional(readOnly = true)
	public CareerListRespDto getCareerList(String partnerId) {
		return CareerListRespDto.builder()
				.careers(careerRepository.findByPartnerId(partnerId)
						.stream()
						.map(CareerDto::new)
						.toList())
				.build();
	}

	private Partner isExist(String uuid) {
		return partnerRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
	}
}
