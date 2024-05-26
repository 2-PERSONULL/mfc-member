package com.mfc.memberservice.member.presentation;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.PartnerService;
import com.mfc.memberservice.member.dto.req.ModifyPartnerReqDto;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.vo.req.ModifyPartnerReqVo;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;

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

	private void checkUuid(String uuid) {
		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}
	}
}
