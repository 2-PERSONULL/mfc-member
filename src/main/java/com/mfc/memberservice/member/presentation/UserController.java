package com.mfc.memberservice.member.presentation;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.UserService;
import com.mfc.memberservice.member.dto.resp.UserProfileResponse;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;
import com.mfc.memberservice.member.vo.resp.BodyTypeRespVo;
import com.mfc.memberservice.member.vo.resp.ProfileRespVo;
import com.mfc.memberservice.member.vo.resp.SizeRespVo;

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

	@GetMapping("/size")
	@Operation(summary = "유저 옷 사이즈 조회 API", description = "topSize, bottomSize, shoeSize 조회")
	public BaseResponse<SizeRespVo> getSize(
			@RequestHeader(name = "userId", defaultValue = "") String userId) {
		return new BaseResponse<>(modelMapper.map(
				userService.getSize(userId), SizeRespVo.class));
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

	@GetMapping("/bodyType")
	@Operation(summary = "유저 체형 조회 API", description = "height, weight, bodyType 조회")
	public BaseResponse<BodyTypeRespVo> getBodyType(
			@RequestHeader(name = "userId", defaultValue = "") String userId) {
		return new BaseResponse<>(modelMapper.map(
				userService.getBodyType(userId), BodyTypeRespVo.class));
	}

	@GetMapping("/profile")
	@Operation(summary = "유저 기본 프로필 조회 API", description = "닉네임, 프로필 이미지 조회")
	public BaseResponse<ProfileRespVo> getProfile(
			@RequestHeader(value = "userId", defaultValue = "") String userId) {
		return new BaseResponse<>(modelMapper.map(
				userService.getProfile(userId), ProfileRespVo.class));
	}

	@GetMapping("/nickname-image/{userId}")
	@Operation(summary = "유저 닉네임 및 프로필 이미지 검색 API", description = "닉네임 및 프로필 이미지 검색")
	public BaseResponse<UserProfileResponse> getNicknameImage(
			@PathVariable String userId) {
		return new BaseResponse<>(modelMapper.map(
				userService.getNicknameImage(userId), UserProfileResponse.class));
	}

	private void checkUuid(String uuid) {
		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}
	}
}
