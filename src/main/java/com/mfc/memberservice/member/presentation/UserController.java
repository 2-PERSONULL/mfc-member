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
import com.mfc.memberservice.common.response.BaseResponseStatus;
import com.mfc.memberservice.member.application.UserService;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "유저 서비스 컨트롤러")
public class UserController {
	private final UserService userService;
	private final ModelMapper modelMapper;

	@PutMapping("/profile")
	@Operation(summary = "유저 프로필 등록 API", description = "회원가입 후 프로필 한 번에 등록 (최초 1회)")
	public BaseResponse<Void> updateProfile(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyUserReqVo vo) {
		checkUuid(uuid);
		userService.updateProfile(uuid, modelMapper.map(vo, ModifyUserReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/size")
	@Operation(summary = "유저 옷 사이즈 수정 API", description = "topSize, bottomSize, shoeSize만 수정 가능")
	public BaseResponse<Void> updateSize(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyUserReqVo vo) {
		checkUuid(uuid);
		userService.updateSize(uuid, modelMapper.map(vo, ModifyUserReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/profileimage")
	@Operation(summary = "유저 프로필 사진 수정 API", description = "profileImage만 수정 가능")
	public BaseResponse<Void> updateProfileImage(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyUserReqVo vo) {
		checkUuid(uuid);
		userService.updateProfileImage(uuid, modelMapper.map(vo, ModifyUserReqDto.class));
		return new BaseResponse<>();
	}

	@PutMapping("/bodytype")
	@Operation(summary = "유저 체형 수정 API", description = "height, weight, bodyType만 수정 가능")
	public BaseResponse<Void> updateBodyType(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyUserReqVo vo) {
		checkUuid(uuid);
		userService.updateBodyType(uuid, modelMapper.map(vo, ModifyUserReqDto.class));
		return new BaseResponse<>();
	}

	private void checkUuid(String uuid) {
		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}
	}
}
