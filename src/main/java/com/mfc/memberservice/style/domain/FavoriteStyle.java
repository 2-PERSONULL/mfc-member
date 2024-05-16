package com.mfc.memberservice.style.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FavoriteStyle {
	@Id
	@GeneratedValue
	private Long id;
	private UUID uuid;
	private Long styleId;

	@Builder
	public FavoriteStyle(Long id, UUID uuid, Long styleId) {
		this.id = id;
		this.uuid = uuid;
		this.styleId = styleId;
	}
}
