package com.mfc.memberservice.member.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.mapping.Selectable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
	@Query("Select m from Member m where m.status = 1 and  m.phone = :phone")
	Optional<Member> findByActivePhone(@Param("phone") String phone);

	Optional<Member> findByPhone(String phone);

	@Query("select m from Member m where m.status = 1 and m.uuid = :uuid")
	Optional<Member> findByUuid(@Param("uuid") String uuid);

	@Query("select m from Member m where m.status = 1 and m.email = :email")
	Optional<Member> findByEmail(@Param("email") String email);
}
