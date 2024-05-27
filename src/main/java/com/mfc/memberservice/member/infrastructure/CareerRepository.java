package com.mfc.memberservice.member.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.Career;

public interface CareerRepository extends JpaRepository<Career, Long> {
	Optional<Career> findByIdAndPartnerId(Long id, String partnerId);
	List<Career> findByPartnerId(String partnerId);

	@Modifying(flushAutomatically = true)
	@Query("delete from Career c where c.id = :id and c.partnerId = :partnerId")
	void deleteByIdAndPartnerId(@Param("id") Long id, @Param("partnerId") String partnerId);

}
