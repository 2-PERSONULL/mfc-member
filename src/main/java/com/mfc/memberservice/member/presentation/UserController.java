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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final ModelMapper modelMapper;

	@PutMapping("/profile")
	public BaseResponse<Void> updateProfile(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody ModifyUserReqVo vo) {
		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}

		userService.updateProfile(uuid, modelMapper.map(vo, ModifyUserReqDto.class));
		return new BaseResponse<>();
	}
}
