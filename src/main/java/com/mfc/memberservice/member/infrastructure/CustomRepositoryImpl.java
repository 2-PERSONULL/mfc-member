package com.mfc.memberservice.member.infrastructure;

import static com.mfc.memberservice.member.domain.QFavoriteStyle.*;
import static com.mfc.memberservice.member.domain.QMember.*;
import static com.mfc.memberservice.member.domain.QPartner.*;
import static com.mfc.memberservice.member.domain.QStyle.*;
import static com.mfc.memberservice.member.domain.QUser.*;

import java.util.List;

import com.mfc.memberservice.member.dto.resp.FavoriteStyleDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {
	private final JPAQueryFactory query;

	@Override
	public ProfileRespDto getUserProfile(String uuid) {
		return query
				.select(Projections.constructor(ProfileRespDto.class,
						user.profileImage.as("profileImage"),
						user.imageAlt.as("imageAlt"),
						user.nickname.as("nickname"),
						member.email.as("email")
				))
				.from(user)
				.join(member)
				.on(user.uuid.eq(member.uuid))
				.where(user.uuid.eq(uuid))
				.fetchOne();
	}

	@Override
	public ProfileRespDto getPartnerProfile(String uuid) {
		return query
				.select(Projections.constructor(ProfileRespDto.class,
						partner.profileImage.as("profileImage"),
						partner.imageAlt.as("imageAlt"),
						partner.nickname.as("nickname"),
						member.email.as("email")
				))
				.from(partner)
				.join(member)
				.on(partner.uuid.eq(member.uuid))
				.where(partner.uuid.eq(uuid))
				.fetchOne();
	}

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
