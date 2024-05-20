package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.response.BaseResponse;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;

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

	private void updateUserNickname(User user, String nickname) {
		userRepository.save(User.builder()
				.id(user.getId())
				.nickname(nickname)
				.profileImage(user.getProfileImage())
				.topSize(user.getTopSize())
				.height(user.getHeight())
				.imageAlt(user.getImageAlt())
				.shoeSize(user.getShoeSize())
				.bottomSize(user.getBottomSize())
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
