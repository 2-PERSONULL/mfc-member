package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.dto.resp.ProfileRespDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

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
}
