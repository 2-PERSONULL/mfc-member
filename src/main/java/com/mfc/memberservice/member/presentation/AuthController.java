package com.mfc.memberservice.member.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.AuthService;
import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.vo.req.SignUpReqVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;
	private final ModelMapper modelMapper;

	@PostMapping("/signup")
	public BaseResponse<Void> signUp(@RequestBody SignUpReqVo vo, @RequestParam String role) {
		authService.signUp(modelMapper.map(vo, SignUpReqDto.class), role);
		return new BaseResponse<>();
	}

}
