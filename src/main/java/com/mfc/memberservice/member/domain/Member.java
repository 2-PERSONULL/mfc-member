package com.mfc.memberservice.member.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.mfc.memberservice.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private UUID uuid;
	@Column(nullable = false, length = 20)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, updatable = false, length = 10)
	private String name;
	@Column(nullable = false)
	private String phone;
	private LocalDate birth;
	@Column(columnDefinition = "TINYINT")
	private Short gender;
	@Enumerated
	@Column(nullable = false)
	private Role role;

	@Builder
	public Member(UUID uuid, String email, String password, String name, String phone, LocalDate birth, Short gender,
			Role role) {
		this.uuid = uuid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.role = role;
	}
}
