package com.mfc.memberservice.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByPhone(String phone);
	Optional<Member> findByUuid(String uuid);
	Optional<Member> findByEmail(String email);
}
