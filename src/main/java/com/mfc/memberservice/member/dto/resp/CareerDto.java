package com.mfc.memberservice.member.dto.resp;

import java.time.LocalDate;

import com.mfc.memberservice.member.domain.Career;

import lombok.Getter;

@Getter
public class CareerDto {
	private Long careerId;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate finishDate;

	public CareerDto(Career career) {
		this.careerId = career.getId();
		this.title = career.getTitle();
		this.description = career.getDescription();
		this.startDate = career.getStartDate();
		this.finishDate = career.getFinishDate();
	}
}
