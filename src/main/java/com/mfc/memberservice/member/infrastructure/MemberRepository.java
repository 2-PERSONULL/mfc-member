package com.mfc.memberservice.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
