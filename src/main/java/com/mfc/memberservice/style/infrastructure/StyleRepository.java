package com.mfc.memberservice.style.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.style.domain.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
}
