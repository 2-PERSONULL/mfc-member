package com.mfc.memberservice.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
	@Query("Select m from Member m where m.status = 1 and  m.phone = :phone")
	Optional<Member> findByActivePhone(@Param("phone") String phone);

	Optional<Member> findByPhone(String phone);
	Optional<Member> findByUuid(String uuid);
	Optional<Member> findByEmail(String email);
}
