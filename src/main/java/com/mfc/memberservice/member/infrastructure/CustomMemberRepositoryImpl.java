package com.mfc.memberservice.member.infrastructure;

import static com.mfc.memberservice.member.domain.QMember.*;
import static com.mfc.memberservice.member.domain.QPartner.*;
import static com.mfc.memberservice.member.domain.QUser.*;

import com.mfc.memberservice.member.domain.QPartner;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {
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
}
