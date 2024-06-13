package com.mfc.memberservice.member.infrastructure;

import static com.mfc.memberservice.member.domain.QFavoriteStyle.*;
import static com.mfc.memberservice.member.domain.QStyle.*;

import java.util.List;

import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {
	private final JPAQueryFactory query;

	@Override
	public List<FavoriteStyleDto> getFavoriteStyles(String uuid) {
		return query
				.select(Projections.constructor(FavoriteStyleDto.class,
						favoriteStyle.id.as("favoriteId"),
						style.id.as("styleId"),
						style.name.as("name")))
				.from(favoriteStyle)
				.join(style)
				.on(favoriteStyle.styleId.eq(style.id))
				.where(favoriteStyle.uuid.eq(uuid))
				.fetch();

	}
}
