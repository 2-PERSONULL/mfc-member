package com.mfc.memberservice.member.dto.resp;

import javax.swing.text.html.Option;

import com.mfc.memberservice.member.domain.PriceOption;

import lombok.Getter;

@Getter
public class OptionDto {
	private Long optionId;
	private String value;
	private Integer price;
	private String description;

	public OptionDto(PriceOption option) {
		this.optionId = option.getId();
		this.value = option.getValue();
		this.price = option.getPrice();
		this.description = option.getDescription();
	}
}
