package com.mfc.memberservice.member.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.PriceOption;

public interface PriceOptionRepository extends JpaRepository<PriceOption, Long> {
	Optional<PriceOption> findByIdAndPartnerId(Long id, String partnerId);
	List<PriceOption> findByPartnerId(String partnerId);

	@Modifying(flushAutomatically = true)
	@Query("delete from PriceOption o where o.partnerId = :partnerId and o.id = :id")
	void deleteOption(@Param("partnerId") String partnerId, @Param("id") Long id);
}
