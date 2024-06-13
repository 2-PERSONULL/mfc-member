package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.jwt.JwtTokenProvider;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.req.ModifyMemberReqDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;
	private final FavoriteStyleRepository favoriteStyleRepository;

	private final PasswordEncoder encoder;
	private final JwtTokenProvider tokenProvider;

	@Override
	public void modifyNickname(String uuid, String role, String nickname) {
		if(role.equals("USER")) {
			User user = userRepository.findByUuid(uuid)
					.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
			updateUserNickname(user, nickname);

		} else if(role.equals("PARTNER")) {
			Partner partner = partnerRepository.findByUuid(uuid)
					.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
			updatePartnerNickname(partner, nickname);
		} else {
			throw new BaseException(NO_EXIT_ROLE);
		}
	}

	@Override
	public void modifyPassword(String uuid, ModifyMemberReqDto dto) {
		Member member = memberRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		memberRepository.save(Member.builder()
				.id(member.getId())
				.email(member.getEmail())
				.name(member.getName())
				.phone(member.getPhone())
				.password(encoder.encode(dto.getPassword()))
				.status(member.getStatus())
				.build()
		);
	}

	@Override
	public void modifyFavoriteStyle(String uuid, ModifyFavoriteStyleReqDto dto) {
		favoriteStyleRepository.deleteByUuid(uuid);

		dto.getFavoriteStyles().stream()
				.forEach(i -> favoriteStyleRepository.save(
						FavoriteStyle.builder()
								.uuid(uuid)
								.styleId(i)
								.build()));
	}

	@Override
	public FavoriteStyleRespDto getFavoriteStyle(String uuid) {
		return FavoriteStyleRespDto.builder()
				.favoriteStyles(favoriteStyleRepository.getFavoriteStyles(uuid)
						.stream()
						.toList())
				.build();
	}

	@Override
	public void resign(String uuid) {
		Member member = memberRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		memberRepository.save(Member.builder()
				.id(member.getId())
				.email(member.getEmail())
				.name(member.getName())
				.phone(member.getPhone())
				.password(member.getPassword())
				.status((short)0)
				.build());

		userRepository.deleteByUuid(uuid);
		partnerRepository.deleteByUuid(uuid);
		favoriteStyleRepository.deleteByUuid(uuid);
	}

	@Override
	public SignInRespDto changeRole(String uuid, String role) {
		if(role.equals("USER")) {
			if(partnerRepository.findByUuid(uuid).isEmpty()) {
				partnerRepository.save(Partner.builder()
						.uuid(uuid)
						.nickname("partner" + (int) (Math.random() * 9000) + 1000)
						.build()
				);
			}
		} else if(role.equals("PARTNER")) {
			if(userRepository.findByUuid(uuid).isEmpty()) {
				userRepository.save(User.builder()
						.uuid(uuid)
						.nickname("user" + (int) (Math.random() * 9000) + 1000)
						.build()
				);
			}
		} else {
			throw new BaseException(NO_EXIT_ROLE);
		}

		return SignInRespDto.builder()
				.uuid(uuid)
				.accessToken(tokenProvider.getAccessToken(uuid))
				.refreshToken(tokenProvider.gerRefreshToken(uuid))
				.build();
	}

	private void updateUserNickname(User user, String nickname) {
		userRepository.save(User.builder()
				.id(user.getId())
				.nickname(nickname)
				.profileImage(user.getProfileImage())
				.topSize(user.getTopSize().toString())
				.height(user.getHeight())
				.weight(user.getWeight())
				.imageAlt(user.getImageAlt())
				.shoeSize(user.getShoeSize())
				.bottomSize(user.getBottomSize().toString())
				.bodyType(user.getBodyType().toString())
				.build()
		);
	}

	private void updatePartnerNickname(Partner partner, String nickname) {
		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.nickname(nickname)
				.profileImage(partner.getProfileImage())
				.imageAlt(partner.getImageAlt())
				.averageDate(partner.getAverageDate())
				.description(partner.getDescription())
				.account(partner.getAccount())
				.build()
		);
	}
}
