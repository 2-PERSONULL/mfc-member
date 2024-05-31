package com.mfc.memberservice.member.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.Sns;

public interface SnsRepository extends JpaRepository<Sns, Long> {
	List<Sns> findByPartnerId(String partnerId);
	List<Sns> findByPartnerCode(String partnerCode);

	@Modifying(flushAutomatically = true)
	@Query("delete from Sns s where s.partnerId = :uuid")
	void deleteByPartnerId(@Param("uuid") String uuid);
}
