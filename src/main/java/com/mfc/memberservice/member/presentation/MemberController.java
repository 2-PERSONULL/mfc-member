package com.mfc.memberservice.member.presentation;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.MemberService;
import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.req.ModifyMemberReqDto;
import com.mfc.memberservice.member.vo.req.ModifyFavoriteStyleReqVo;
import com.mfc.memberservice.member.vo.req.ModifyMemberReqVo;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;
import com.mfc.memberservice.member.vo.resp.ProfileRespVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final ModelMapper modelMapper;

	@GetMapping
	public BaseResponse<ProfileRespVo> getProfile(@RequestHeader HttpHeaders header) {
		List<String> uuid = header.get("UUID");
		List<String> role = header.get("Role");

		if(uuid == null || role == null) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}

		return new BaseResponse<>(modelMapper.map(
				memberService.getProfile(uuid.get(0), role.get(0)), ProfileRespVo.class));
	}

	@PutMapping("/nickname")
	public BaseResponse<Void> modifyNickname(@RequestHeader HttpHeaders header,
			@RequestBody ModifyUserReqVo vo) {
		List<String> uuid = header.get("UUID");
		List<String> role = header.get("Role");

		log.info("nickname={}", vo.getNickname());

		if(uuid == null || role == null) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}

		memberService.modifyNickname(uuid.get(0), role.get(0), vo.getNickname());
		return new BaseResponse<>();
	}

	@PutMapping("/password")
	public BaseResponse<Void> modifyPassword(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated ModifyMemberReqVo vo) {

		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}

		memberService.modifyPassword(uuid, modelMapper.map(vo, ModifyMemberReqDto.class));
		return new BaseResponse<>();
	}

	@PostMapping("/favoritestyle")
	public BaseResponse<Void> modifyStyle(
			@RequestHeader(name = "UUID", defaultValue = "") String uuid,
			@RequestBody @Validated ModifyFavoriteStyleReqVo vo) {

		if(!StringUtils.hasText(uuid)) {
			throw new BaseException(NO_REQUIRED_HEADER);
		}

		memberService.modifyFavoriteStyle(uuid, modelMapper.map(vo, ModifyFavoriteStyleReqDto.class));
		return new BaseResponse<>();
	}
}
