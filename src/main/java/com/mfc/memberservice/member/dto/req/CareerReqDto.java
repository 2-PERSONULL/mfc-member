package com.mfc.memberservice.member.dto.req;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CareerReqDto {
	private String title;
	private LocalDate startDate;
	private LocalDate finishDate;
	private String description;
}
