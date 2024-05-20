package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
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
		if(role.equals("user")) {
			User user = userRepository.findByUuid(uuid)
					.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));
			return null;


		} else if(role.equals("partner")) {
			return null;

		} else {
			throw new BaseException(NO_EXIT_ROLE);
		}
	}
}
