package com.mfc.memberservice.member.dto.req;

import java.util.List;

import com.mfc.memberservice.member.vo.req.SnsVo;

import lombok.Getter;

@Getter
public class UpdateSnsReqDto {
	private List<SnsVo> sns;
}
