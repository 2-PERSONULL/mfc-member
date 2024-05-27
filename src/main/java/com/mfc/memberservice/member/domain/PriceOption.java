package com.mfc.memberservice.member.domain;

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
public class PriceOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, updatable = false)
	private String partnerId;
	@Column(nullable = false, length = 50)
	private String value;
	@Column(nullable = false)
	private Integer price;
	@Column(length = 100)
	private String description;

	@Builder
	public PriceOption(Long id, String partnerId, String value, Integer price, String description) {
		this.id = id;
		this.partnerId = partnerId;
		this.value = value;
		this.price = price;
		this.description = description;
	}
}
