package com.mfc.memberservice.member.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Career {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false, nullable = false)
	private String partnerId;
	@Column(updatable = false, nullable = false, length = 30)
	private String partnerCode;
	@Column(length = 50, nullable = false)
	private String title;
	@Column(length = 100)
	private String description;
	private LocalDate startDate;
	private LocalDate finishDate;

	@Builder
	public Career(Long id, String partnerId, String title, String description, LocalDate startDate,
			LocalDate finishDate, String partnerCode) {
		this.id = id;
		this.partnerId = partnerId;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.partnerCode = partnerCode;
	}
}
