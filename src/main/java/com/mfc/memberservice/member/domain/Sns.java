package com.mfc.memberservice.member.domain;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.enums.SnsType;

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
public class Sns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String partnerId;
	@Column(updatable = false, nullable = false, length = 30)
	private String partnerCode;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SnsType type;
	@Column(nullable = false)
	private String snsUrl;

	@Builder
	public Sns(Long id, String partnerId, String type, String snsUrl, String partnerCode) {
		this.id = id;
		this.partnerId = partnerId;

		try {
			this.type = SnsType.valueOf(type);
		} catch (NullPointerException e) {
			this.type = null;
		} catch (IllegalArgumentException e) {
			throw new BaseException(INVALID_SNS_TYPE);
		}

		this.snsUrl = snsUrl;
		this.partnerCode = partnerCode;
	}
}
