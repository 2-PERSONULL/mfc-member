package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.ModifyFavoriteStyleReqDto;
import com.mfc.memberservice.member.dto.req.ModifyMemberReqDto;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;
import com.mfc.memberservice.style.domain.FavoriteStyle;
import com.mfc.memberservice.style.infrastructure.FavoriteStyleRepository;

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

	@Override
	@Transactional(readOnly = true)
	public ProfileRespDto getProfile(String uuid, String role) {
		ProfileRespDto profile = null;

		if(role.equals("USER")) {
			profile = memberRepository.getUserProfile(uuid);
		} else if(role.equals("PARTNER")) {
			profile = memberRepository.getPartnerProfile(uuid);
		} else {
			throw new BaseException(NO_EXIT_ROLE);
		}

		if(profile == null) {
			throw new BaseException(MEMBER_NOT_FOUND);
		}

		return profile;
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
	public void modifyPassword(String uuid, ModifyMemberReqDto dto) {
		Member member = memberRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		memberRepository.save(Member.builder()
				.id(member.getId())
				.password(encoder.encode(dto.getPassword()))
				.role(member.getRole())
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

	private void updateUserNickname(User user, String nickname) {
		userRepository.save(User.builder()
				.id(user.getId())
				.nickname(nickname)
				.profileImage(user.getProfileImage())
				.topSize(user.getTopSize())
				.height(user.getHeight())
				.weight(user.getWeight())
				.imageAlt(user.getImageAlt())
				.shoeSize(user.getShoeSize())
				.bottomSize(user.getBottomSize())
				.bodyType(user.getBodyType())
				.build()
		);
	}

	private void updatePartnerNickname(Partner partner, String nickname) {
		partnerRepository.save(Partner.builder()
				.id(partner.getId())
				.nickname(nickname)
				.profileImage(partner.getProfileImage())
				.imageAlt(partner.getImageAlt())
				.average_date(partner.getAverage_date())
				.description(partner.getDescription())
				.account(partner.getAccount())
				.build()
		);
	}
}
