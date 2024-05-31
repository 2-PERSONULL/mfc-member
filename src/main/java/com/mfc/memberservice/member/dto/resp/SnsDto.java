package com.mfc.memberservice.member.dto.resp;

import com.mfc.memberservice.member.domain.Sns;

import lombok.Getter;

@Getter
public class SnsDto {
	private Long id;
	private String type;
	private String snsUrl;

	public SnsDto(Sns sns) {
		this.id = sns.getId();
		this.type = sns.getType().toString();
		this.snsUrl = sns.getSnsUrl();
	}
}
