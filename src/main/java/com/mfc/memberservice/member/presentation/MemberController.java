package com.mfc.memberservice.member.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	@GetMapping
	public BaseResponse<?> getProfile(@RequestHeader("UUID") String uuid,
			@RequestHeader("Role") String role) {
		return new BaseResponse<>();
	}
}
