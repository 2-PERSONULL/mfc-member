package com.mfc.memberservice.member.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfc.memberservice.member.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNickname(String nickname);
	Optional<User> findByUuid(String uuid);
	@Modifying(flushAutomatically = true)
	@Query("Delete from User u where u.uuid = :uuid")
	void deleteByUuid(@Param("uuid") String uuid);
}
