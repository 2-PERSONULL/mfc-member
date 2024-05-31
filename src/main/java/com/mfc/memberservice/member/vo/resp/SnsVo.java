package com.mfc.memberservice.member.vo.resp;

import com.mfc.memberservice.member.domain.Sns;

import lombok.Getter;

@Getter
public class SnsVo {
	private Long id;
	private String type;
	private String snsUrl;
}
