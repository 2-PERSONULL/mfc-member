package com.mfc.memberservice.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.member.domain.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
}
