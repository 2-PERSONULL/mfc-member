package com.mfc.memberservice.member.domain;

import java.util.UUID;

import com.mfc.memberservice.common.entity.BaseEntity;

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
public class Partner extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	@Column(nullable = false, length = 20)
	private String nickname;
	private String profileImage;
	@Column(length = 50)
	private String imageAlt;
	private String account;
	private String description;
	private Integer average_date;

	@Builder
	public Partner(Long id, String uuid, String nickname, String profileImage, String account, String description,
			Integer average_date, String imageAlt) {
		this.id = id;
		this.uuid = uuid;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.imageAlt = imageAlt;
		this.account = account;
		this.description = description;
		this.average_date = average_date;
	}
}
