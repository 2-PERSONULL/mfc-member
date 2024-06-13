package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.Career;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.PriceOption;
import com.mfc.memberservice.member.domain.Sns;
import com.mfc.memberservice.member.dto.req.CareerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.OptionReqDto;
import com.mfc.memberservice.member.dto.req.UpdateSnsReqDto;
import com.mfc.memberservice.member.dto.resp.CareerDto;
import com.mfc.memberservice.member.dto.resp.CareerListRespDto;
import com.mfc.memberservice.member.dto.resp.OptionDto;
import com.mfc.memberservice.member.dto.resp.OptionListRespDto;
import com.mfc.memberservice.member.dto.resp.PartnerPortfolioRespDto;
import com.mfc.memberservice.member.dto.resp.SnsDto;
import com.mfc.memberservice.member.dto.resp.SnsListRespDto;
import com.mfc.memberservice.member.infrastructure.CareerRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.PriceOptionRepository;
import com.mfc.memberservice.member.infrastructure.SnsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerServiceImpl implements PartnerService {
	private final PartnerRepository partnerRepository;
	private final SnsRepository snsRepository;
	private final CareerRepository careerRepository;
	private final PriceOptionRepository optionsRepository;

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
				.averageDate(partner.getAverageDate())
				.bank(partner.getBank())
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averagePrice(partner.getAveragePrice())
				.build());
	}

	@Override
	public void updateSns(String uuid, UpdateSnsReqDto dto) {
		isExist(uuid);
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

	@Override
	public void createOption(String uuid, OptionReqDto dto) {
		isExist(uuid);
		optionsRepository.save(PriceOption.builder()
				.partnerId(uuid)
				.value(dto.getValue())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.build()
		);
	}

	@Override
	public void updateOption(String partnerId, Long optionId, OptionReqDto dto) {
		PriceOption option = optionsRepository.findByIdAndPartnerId(optionId, partnerId)
				.orElseThrow(() -> new BaseException(OPTION_NOT_FOUND));

		optionsRepository.save(PriceOption.builder()
				.id(optionId)
				.value(dto.getValue())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.build()
		);
	}

	@Override
	public void deleteOption(String partnerId, Long optionId) {
		optionsRepository.deleteOption(partnerId, optionId);
	}

	@Override
	public void updateDescription(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(partner.getProfileImage())
				.nickname(partner.getNickname())
				.imageAlt(partner.getImageAlt())
				.description(dto.getDescription()) // 수정
				.account(partner.getAccount())
				.averageDate(partner.getAverageDate())
				.bank(partner.getBank())
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averagePrice(partner.getAveragePrice())
				.build());
	}

	@Override
	public void updateAccount(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(partner.getProfileImage())
				.nickname(partner.getNickname())
				.imageAlt(partner.getImageAlt())
				.description(partner.getDescription())
				.account(dto.getAccount()) // 수정
				.averageDate(partner.getAverageDate())
				.bank(dto.getBank()) // 수정
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averagePrice(partner.getAveragePrice())
				.build());
	}

	@Override
	public void updateAverageTime(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(partner.getProfileImage())
				.nickname(partner.getNickname())
				.imageAlt(partner.getImageAlt())
				.description(partner.getDescription())
				.account(partner.getAccount())
				.averageDate(dto.getAverageDate()) // 수정
				.bank(partner.getBank())
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averagePrice(partner.getAveragePrice())
				.build());
	}

	@Override
	public void updateChatTime(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(partner.getProfileImage())
				.nickname(partner.getNickname())
				.imageAlt(partner.getImageAlt())
				.description(partner.getDescription())
				.account(partner.getAccount())
				.averageDate(partner.getAverageDate())
				.bank(partner.getBank())
				.startTime(dto.getStartTime()) // 수정
				.endTime(dto.getEndTime()) // 수정
				.averagePrice(partner.getAveragePrice())
				.build());
	}

	@Override
	public void updateAveragePrice(String uuid, ModifyPartnerReqDto dto) {
		Partner partner = isExist(uuid);

		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.profileImage(partner.getProfileImage())
				.nickname(partner.getNickname())
				.imageAlt(partner.getImageAlt())
				.description(partner.getDescription())
				.account(partner.getAccount())
				.averageDate(partner.getAverageDate())
				.bank(partner.getBank())
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averagePrice(dto.getAveragePrice()) // 수정
				.build()
		);
	}

	@Override
	public OptionListRespDto getOptionList(String partnerId) {
		return OptionListRespDto.builder()
				.options(optionsRepository.findByPartnerId(partnerId)
						.stream()
						.map(OptionDto::new)
						.toList())
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public PartnerPortfolioRespDto getPortfolio(String partnerId) {
		Partner partner = partnerRepository.findByUuid(partnerId)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		return PartnerPortfolioRespDto.builder()
				.description(partner.getDescription())
				.startTime(partner.getStartTime())
				.endTime(partner.getEndTime())
				.averageDate(partner.getAverageDate())
				.averagePrice(partner.getAveragePrice())
				.build();
	}

	private Partner isExist(String uuid) {
		return partnerRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
	}
}
