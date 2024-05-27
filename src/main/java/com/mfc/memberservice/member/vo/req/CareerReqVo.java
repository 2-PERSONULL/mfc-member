package com.mfc.memberservice.member.vo.req;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CareerReqVo {
	@NotBlank
	private String title;
	private LocalDate startDate;
	private LocalDate finishDate;
	private String description;
}
