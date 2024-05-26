package com.mfc.memberservice.member.vo.resp;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class CareerVo {
	private Long careerId;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate finishDate;
}
