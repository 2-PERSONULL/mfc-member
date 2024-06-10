package com.mfc.memberservice.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.member.dto.resp.StyleDto;
import com.mfc.memberservice.member.dto.resp.StyleListRespDto;
import com.mfc.memberservice.member.infrastructure.StyleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StyleServiceImpl implements StyleService {
	private final StyleRepository styleRepository;

	@Override
	public StyleListRespDto getStyles() {
		return StyleListRespDto.builder()
				.styles(styleRepository.findAll()
						.stream()
						.map(StyleDto::new)
						.toList())
				.build();
	}
}
