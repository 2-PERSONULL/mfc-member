package com.mfc.memberservice.member.presentation;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.PartnerService;
import com.mfc.memberservice.member.dto.req.CareerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.OptionReqDto;
import com.mfc.memberservice.member.dto.req.UpdateSnsReqDto;
import com.mfc.memberservice.member.vo.req.CareerReqVo;
import com.mfc.memberservice.member.vo.req.ModifyPartnerReqVo;
import com.mfc.memberservice.member.vo.req.OptionReqVo;
import com.mfc.memberservice.member.vo.req.UpdateSnsReqVo;
import com.mfc.memberservice.member.vo.resp.CareerListRespVo;
import com.mfc.memberservice.member.vo.resp.OptionListRespVo;
import com.mfc.memberservice.member.vo.resp.SnsListRespVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/partners")
@RequiredArgsConstructor
@Tag(name = "partners", description = "파트너 서비스 컨트롤러")
public class PartnerController {
	private final PartnerService partnerService;
	private final ModelMapper modelMapper;

	@PutMapping("/profileimage")
	@Operation(summary = "파트너 프로필 사진 수정 API", description = "profileImage만 수정 가능")
	public BaseResponse<Void> updateProfileImage(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyPartnerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateProfileImage(uuid, modelMapper.map(vo, ModifyPartnerReqDto.class));
		return new BaseResponse<>();
	}

	@PostMapping("/sns")
	@Operation(summary = "파트너 SNS 등록 API", description = "파트너 포트폴리오 : sns 등록")
	public BaseResponse<Void> updateSns(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody UpdateSnsReqVo vo) {
		checkUuid(uuid);
		partnerService.updateSns(uuid, modelMapper.map(vo, UpdateSnsReqDto.class));
		return new BaseResponse<>();
	}

	@GetMapping("/sns/{partnerId}")
	@Operation(summary = "파트너 SNS 조회 API", description = "파트너 포트폴리오 : sns 목록 조회")
	public BaseResponse<SnsListRespVo> updateSns(@PathVariable String partnerId) {
		return new BaseResponse<>(modelMapper.map(
				partnerService.getSnsList(partnerId), SnsListRespVo.class));
	}

	@PostMapping("/career")
	@Operation(summary = "파트너 경력 등록 API", description = "파트너 포트폴리오 : 경력 등록")
	public BaseResponse<Void> createCareer(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated CareerReqVo vo) {
		checkUuid(uuid);
		partnerService.createCareer(uuid, modelMapper.map(vo, CareerReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/career/{careerId}")
	@Operation(summary = "파트너 경력 수정 API", description = "파트너 포트폴리오 : 경력 수정")
	public BaseResponse<Void> modifyCareer(@PathVariable Long careerId,
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated CareerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateCareer(uuid, careerId, modelMapper.map(vo, CareerReqDto.class));
		return new BaseResponse<>();
	}

	@DeleteMapping("/career/{careerId}")
	@Operation(summary = "파트너 경력 삭제 API", description = "파트너 포트폴리오 : 경력 삭제")
	public BaseResponse<Void> deleteCareer(@PathVariable Long careerId,
			@RequestHeader(value = "UUID", defaultValue = "") String uuid) {
		checkUuid(uuid);
		partnerService.deleteCareer(uuid, careerId);
		return new BaseResponse<>();
	}

	@GetMapping("/career/{partnerId}")
	@Operation(summary = "파트너 경력 조회 API", description = "파트너 포트폴리오 : 경력 목록 조회")
	public BaseResponse<CareerListRespVo> getCareerList(@PathVariable String partnerId) {
		return new BaseResponse<>(modelMapper.map(partnerService.getCareerList(partnerId), CareerListRespVo.class));
	}

	@PostMapping("/option")
	@Operation(summary = "파트너 옵션 등록 API", description = "파트너 포트폴리오 : 옵션 등록")
	public BaseResponse<Void> createOption(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated OptionReqVo vo) {
		checkUuid(uuid);
		partnerService.createOption(uuid, modelMapper.map(vo, OptionReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/option/{optionId}")
	@Operation(summary = "파트너 옵션 수정 API", description = "파트너 포트폴리오 : 옵션 수정")
	public BaseResponse<Void> modifyOption(@PathVariable Long optionId,
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated OptionReqVo vo) {
		checkUuid(uuid);
		partnerService.updateOption(uuid, optionId, modelMapper.map(vo, OptionReqDto.class));
		return new BaseResponse<>();
	}

	@DeleteMapping("/option/{optionId}")
	@Operation(summary = "파트너 옵션 삭제 API", description = "파트너 포트폴리오 : 옵션 삭제")
	public BaseResponse<Void> deleteOption(@PathVariable Long optionId,
			@RequestHeader(value = "UUID", defaultValue = "") String uuid) {
		checkUuid(uuid);
		partnerService.deleteOption(uuid, optionId);
		return new BaseResponse<>();
	}

	@GetMapping("/option/{partnerId}")
	@Operation(summary = "파트너 가격 옵션 조회 API", description = "파트너 포트폴리오 : 가격 옵션 조회")
	public BaseResponse<OptionListRespVo> getOptionList(@PathVariable String partnerId) {
		return new BaseResponse<>(modelMapper.map(partnerService.getOptionList(partnerId), OptionListRespVo.class));
	}

	@PutMapping("/description")
	@Operation(summary = "파트너 한 줄 소개 수정 API", description = "파트너 소개 수정 : description만 수정 가능")
	public BaseResponse<Void> updateDescription(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyPartnerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateDescription(uuid, modelMapper.map(vo, ModifyPartnerReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/account")
	@Operation(summary = "파트너 계좌 수정 API", description = "파트너 소개 수정 : bank, account만 수정 가능")
	public BaseResponse<Void> updateAccount(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyPartnerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateAccount(uuid, modelMapper.map(vo, ModifyPartnerReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/chattime")
	@Operation(summary = "파트너 채팅 가능 시간 수정 API", description = "파트너 소개 수정 : startTime, endTime만 수정 가능")
	public BaseResponse<Void> updateChatTime(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyPartnerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateChatTime(uuid, modelMapper.map(vo, ModifyPartnerReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/averageDate")
	@Operation(summary = "파트너 코디 평균 소요 시간 수정 API", description = "파트너 소개 수정 : averageDate만 수정 가능")
	public BaseResponse<Void> updateAverageDate(
			@RequestHeader(value = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyPartnerReqVo vo) {
		checkUuid(uuid);
		partnerService.updateAverageTime(uuid, modelMapper.map(vo, ModifyPartnerReqDto.class));
		return new BaseResponse<>();
	}

	private void checkUuid(String uuid) {
		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}
	}
}
