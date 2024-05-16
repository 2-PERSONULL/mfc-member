package com.mfc.memberservice.member.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
	Optional<Partner> findByNickname(String nickname);
}
