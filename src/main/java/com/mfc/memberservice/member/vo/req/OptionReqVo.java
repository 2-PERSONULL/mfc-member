package com.mfc.memberservice.member.vo.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OptionReqVo {
	@NotBlank
	private String value;
	@Min(value = 0)
	private Integer price;
	private String description;
}
