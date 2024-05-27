package com.mfc.memberservice.member.dto.req;

import com.mfc.memberservice.member.domain.Sns;

import lombok.Getter;

@Getter
public class SnsDto {
	private String type;
	private String snsUrl;

	public SnsDto(Sns sns) {
		this.type = sns.getType();
		this.snsUrl = sns.getSnsUrl();
	}
}
