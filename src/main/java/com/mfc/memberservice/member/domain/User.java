package com.mfc.memberservice.member.domain;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import com.mfc.memberservice.common.entity.BaseEntity;
import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.enums.BodyType;
import com.mfc.memberservice.member.domain.enums.SizeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private Integer weight;
	@Enumerated(EnumType.STRING)
	private SizeType topSize;
	@Enumerated(EnumType.STRING)
	private SizeType bottomSize;
	private Integer shoeSize;
	@Enumerated(EnumType.STRING)
	private BodyType bodyType;

	@Builder
	public User(Long id, String uuid, String nickname, String profileImage, Integer height, Integer weight,
			String topSize, String bottomSize, Integer shoeSize, String imageAlt, String bodyType) {
		this.id = id;
		this.uuid = uuid;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.imageAlt = imageAlt;
		this.height = height;
		this.weight = weight;

		try {
			this.topSize = SizeType.valueOf(topSize);
		} catch (NullPointerException e) {
			this.topSize = null;
		} catch (IllegalArgumentException e) {
			throw new BaseException(INVALID_SIZE_TYPE);
		}

		try {
			this.bottomSize = SizeType.valueOf(bottomSize);
		} catch (NullPointerException e) {
			this.bottomSize = null;
		} catch (IllegalArgumentException e) {
			throw new BaseException(INVALID_SIZE_TYPE);
		}

		this.shoeSize = shoeSize;

		try {
			this.bodyType = BodyType.valueOf(bodyType);
		} catch (NullPointerException e) {
			this.bodyType = null;
		} catch (IllegalArgumentException e) {
			throw new BaseException(INVALID_BODY_TYPE);
		}
	}
}
