package com.mfc.memberservice.style.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfc.memberservice.style.domain.FavoriteStyle;

public interface FavoriteStyleRepository extends JpaRepository<FavoriteStyle, Long> {
}
