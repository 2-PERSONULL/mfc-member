package com.mfc.memberservice.member.domain;

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
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String uuid;
	@Column(nullable = false, length = 20)
	private String nickname;
	private String profileImage;
	@Column(length = 50)
	private String imageAlt;
	private Integer height;
	@Column(length = 20)
	private String topSize;
	@Column(length = 20)
	private String bottomSize;
	private Integer shoeSize;

	@Builder
	public User(Long id, String uuid, String nickname, String profileImage, Integer height, String topSize,
			String bottomSize, Integer shoeSize, String imageAlt) {
		this.id = id;
		this.uuid = uuid;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.imageAlt = imageAlt;
		this.height = height;
		this.topSize = topSize;
		this.bottomSize = bottomSize;
		this.shoeSize = shoeSize;
	}
}
