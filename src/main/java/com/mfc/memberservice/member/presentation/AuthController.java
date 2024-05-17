package com.mfc.memberservice.member.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.AuthService;
import com.mfc.memberservice.member.dto.req.SignInReqDto;
import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.dto.req.SmsReqDto;
import com.mfc.memberservice.member.vo.req.SignInReqVo;
import com.mfc.memberservice.member.vo.req.SignUpReqVo;
import com.mfc.memberservice.member.vo.req.SmsReqVo;
import com.mfc.memberservice.member.vo.resp.SignInRespVo;

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

	@PostMapping("/sms/send")
	public BaseResponse<Void> sendSms(@RequestBody SmsReqVo vo) {
		authService.sendSms(modelMapper.map(vo, SmsReqDto.class));
		return new BaseResponse<>();
	}

	@PostMapping("/sms/verify")
	public BaseResponse<Void> verifySms(@RequestBody SmsReqVo vo) {
		authService.verifyCode(modelMapper.map(vo, SmsReqDto.class));
		return new BaseResponse<>();
	}

	@GetMapping("/nickname/{nickname}")
	public BaseResponse<Boolean> verifyNickname(@PathVariable String nickname, @RequestParam String role) {
		return new BaseResponse<>(authService.verifyNickname(nickname, role));
	}

	@PostMapping("/signin")
	public BaseResponse<SignInRespVo> signIn(@RequestBody SignInReqVo vo) {
		return new BaseResponse<>(
				modelMapper.map(authService.signIn(modelMapper.map(vo, SignInReqDto.class)),
						SignInRespVo.class)
		);
	}

}
