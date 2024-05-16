package com.mfc.memberservice.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
