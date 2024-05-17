package com.mfc.memberservice.member.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Member extends BaseEntity implements UserDetails {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return uuid.toString();
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
