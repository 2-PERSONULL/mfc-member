package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;
import static com.mfc.memberservice.member.domain.Role.*;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.Role;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;
import com.mfc.memberservice.style.domain.FavoriteStyle;
import com.mfc.memberservice.style.infrastructure.FavoriteStyleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
	private final MemberRepository memberRepository;
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;
	private final FavoriteStyleRepository favoriteStyleRepository;

	private final BCryptPasswordEncoder encoder;;

	@Override
	public void signUp(SignUpReqDto dto, String role) {
		Role eRole = null;

		if(role.equals("user")) { // 유저
			eRole = USER;
			Member member = createMember(dto, eRole);
			createUser(member.getUuid(), dto.getNickname());
			insertFavoriteStyle(member.getUuid(), dto.getFavoriteStyles());
		} else if(role.equals("partner")) { // 파트너
			eRole = PARTNER;
			Member member = createMember(dto, eRole);
			createPartner(member.getUuid(), dto.getNickname());
			insertFavoriteStyle(member.getUuid(), dto.getFavoriteStyles());
		} else { // 이외의 파라미터 : 예외 발생
			throw new BaseException(NO_EXIT_ROLE);
		}
	}

	// 회원 공통 정보 저장 (유저, 파트너)
	private Member createMember(SignUpReqDto dto, Role role) {
		return memberRepository.save(Member.builder()
				.email(dto.getEmail())
				.password(encoder.encode(dto.getPassword()))
				.name(dto.getName())
				.birth(dto.getBirth())
				.phone(dto.getPhone())
				.gender(dto.getGender())
				.uuid(UUID.randomUUID())
				.role(role)
				.build());
	}

	// 회원가입 시, 유저 정보 저장
	private void createUser(UUID uuid, String nickname) {
		userRepository.save(User.builder()
				.uuid(uuid)
				.nickname(nickname)
				.build());
	}

	// 회원가입 시, 파트너 정보 저장
	private void createPartner(UUID uuid, String nickname) {
		partnerRepository.save(Partner.builder()
				.uuid(uuid)
				.nickname(nickname)
				.build());
	}

	// 선호 스타일 저장
	private void insertFavoriteStyle(UUID uuid, List<Long> styles) {
		styles.stream()
				.forEach(i -> favoriteStyleRepository.save(
						FavoriteStyle.builder()
								.uuid(uuid)
								.styleId(i)
								.build()));
	}
}
