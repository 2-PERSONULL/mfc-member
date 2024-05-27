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
public class Sns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String partnerId;
	@Column(length = 50)
	private String type;
	private String snsUrl;

	@Builder
	public Sns(Long id, String partnerId, String type, String snsUrl) {
		this.id = id;
		this.partnerId = partnerId;
		this.type = type;
		this.snsUrl = snsUrl;
	}
}
