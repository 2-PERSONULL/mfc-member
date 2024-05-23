package com.mfc.memberservice.member.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mfc.memberservice.common.entity.BaseEntity;

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
public class Member extends BaseEntity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String uuid;
	@Column(nullable = false, length = 20, updatable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, updatable = false, length = 10)
	private String name;
	@Column(nullable = false, updatable = false)
	private String phone;
	@Column(updatable = false)
	private LocalDate birth;
	@Column(columnDefinition = "TINYINT", updatable = false)
	private Short gender;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	@Column(columnDefinition = "TINYINT")
	private Short status;

	@Builder
	public Member(Long id, String uuid, String email, String password, String name, String phone, LocalDate birth,
			Short gender, Role role, Short status) {
		this.id = id;
		this.uuid = uuid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.role = role;
		this.status = status;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return uuid;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
