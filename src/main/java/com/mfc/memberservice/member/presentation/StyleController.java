package com.mfc.memberservice.member.presentation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.application.StyleService;
import com.mfc.memberservice.member.vo.resp.StyleListRespVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/styles")
@Tag(name = "styles", description = "스타일 컨트롤러")
public class StyleController {
	private final StyleService styleService;
	private final ModelMapper modelMapper;

	@GetMapping
	@Operation(summary = "스타일 카테고리 목록 조회 API", description = "스타일 카테고리 목록 조회")
	public BaseResponse<StyleListRespVo> getStyles() {
		return new BaseResponse<>(modelMapper.map(
				styleService.getStyles(), StyleListRespVo.class));
	}
}
