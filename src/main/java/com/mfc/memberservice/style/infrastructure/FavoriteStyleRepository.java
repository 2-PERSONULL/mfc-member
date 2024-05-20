package com.mfc.memberservice.style.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mfc.memberservice.style.domain.FavoriteStyle;

public interface FavoriteStyleRepository extends JpaRepository<FavoriteStyle, Long> {
	@Modifying(clearAutomatically = true)
	@Query("delete from FavoriteStyle fs where fs.uuid = :uuid")
	void deleteByUuid(String uuid);
}
