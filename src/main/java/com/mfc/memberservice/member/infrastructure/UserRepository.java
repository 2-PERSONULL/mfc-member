package com.mfc.memberservice.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
