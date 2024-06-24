package com.mfc.memberservice.member.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
	Optional<Partner> findByNickname(String nickname);
	Optional<Partner> findByUuid(String Uuid);

	@Query("SELECT p FROM Partner p WHERE p.uuid IN :partnerIds")
	List<Partner> findByPartnerIds(@Param("partnerIds") List<String> partnerIds);

	@Modifying(flushAutomatically = true)
	@Query("Delete from Partner p where p.uuid = :uuid")
	void deleteByUuid(@Param("uuid") String uuid);
}
