package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.jwt.JwtTokenProvider;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.resp.FavoriteStyleRespDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;
	private final FavoriteStyleRepository favoriteStyleRepository;

	private final JwtTokenProvider tokenProvider;

	@Transactional(readOnly = true)
	@Override
	public boolean verifyNickname(String nickname) {
		return (userRepository.findByNickname(nickname).isEmpty()
				&& partnerRepository.findByNickname(nickname).isEmpty());
	}


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
	public void modifyFavoriteStyle(String uuid, ModifyFavoriteStyleReqDto dto) {
		favoriteStyleRepository.deleteByUuid(uuid);

		dto.getFavoriteStyles()
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
				.topSize(user.getTopSize() == null ? null : user.getTopSize().toString())
				.height(user.getHeight())
				.weight(user.getWeight())
				.imageAlt(user.getImageAlt())
				.shoeSize(user.getShoeSize())
				.bottomSize(user.getBottomSize() == null ? null : user.getBottomSize().toString())
				.bodyType(user.getBodyType() == null ? null : user.getBodyType().toString())
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
