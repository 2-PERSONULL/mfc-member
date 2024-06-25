package com.mfc.memberservice.member.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;

public interface FavoriteStyleRepository extends JpaRepository<FavoriteStyle, Long>, CustomRepository {
	@Modifying(clearAutomatically = true)
	@Query("delete from FavoriteStyle fs where fs.uuid = :uuid")
	void deleteByUuid(String uuid);

	@Query("select fs.uuid from FavoriteStyle fs where fs.styleId = :styleId")
	List<String> findByStyleId(@Param("styleId") Long styleId);

	@Query("select distinct fs.uuid from FavoriteStyle fs where fs.styleId in :styleIds")
	List<String> findByStyleIds(@Param("styleIds") List<Long> styleIds);

	@Query("select fs.styleId from FavoriteStyle fs where fs.uuid = :uuid")
	List<Long> findStyleIdsByUuid(@Param("uuid") String uuid);
}
