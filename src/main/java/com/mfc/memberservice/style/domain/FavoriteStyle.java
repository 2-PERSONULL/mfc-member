package com.mfc.memberservice.style.domain;

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
public class FavoriteStyle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String uuid;
	private Long styleId;

	@Builder
	public FavoriteStyle(Long id, String uuid, Long styleId) {
		this.id = id;
		this.uuid = uuid;
		this.styleId = styleId;
	}
}
