package com.mfc.memberservice.member.dto.resp;

import java.util.List;

import com.mfc.memberservice.member.dto.req.SnsDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SnsListRespDto {
	private List<SnsDto> sns;
}
