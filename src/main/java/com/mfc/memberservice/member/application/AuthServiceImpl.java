package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;
import static com.mfc.memberservice.member.domain.Role.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.common.jwt.JwtTokenProvider;
import com.mfc.memberservice.common.sms.SmsUtil;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.Role;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.SignInReqDto;
import com.mfc.memberservice.member.dto.req.SignUpReqDto;
import com.mfc.memberservice.member.dto.req.SmsReqDto;
import com.mfc.memberservice.member.dto.resp.SignInRespDto;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.SmsRepository;
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
	private final SmsRepository smsRepository;

	private final SmsUtil smsUtil;

	private final BCryptPasswordEncoder encoder;
	private final JwtTokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;

	@Override
	public void signUp(SignUpReqDto dto) {
		Role eRole = null;

		if(dto.getRole().equals("USER")) { // 유저
			eRole = USER;
			Member member = createMember(dto, eRole);
			createUser(member.getUuid(), dto.getNickname());
			insertFavoriteStyle(member.getUuid(), dto.getFavoriteStyles());
		} else if(dto.getRole().equals("PARTNER")) { // 파트너
			eRole = PARTNER;
			Member member = createMember(dto, eRole);
			createPartner(member.getUuid(), dto.getNickname());
			insertFavoriteStyle(member.getUuid(), dto.getFavoriteStyles());
		} else { // 이외의 파라미터 : 예외 발생
			throw new BaseException(NO_EXIT_ROLE);
		}
	}

	@Override
	public void sendSms(SmsReqDto dto) {
		String to = dto.getPhone();
		if(isDuplicate(to)) {
			throw new BaseException(DUPLICATED_MEMBERS);
		}

		int random = (int) (Math.random() * 900000) + 1000;
		String code = String.valueOf(random);

		smsUtil.sendSms(to, code); // 문자 전송
		smsRepository.createSmsCode(to, code); // 인증번호 redis에 저장
	}

	@Override
	public void verifyCode(SmsReqDto dto) {
		if(!isVerify(dto)) {
			throw new BaseException(MESSAGE_VALID_FAILED);
		}

		smsRepository.removeSmsCode(dto.getPhone());
	}

	@Transactional(readOnly = true)
	@Override
	public boolean verifyNickname(String nickname) {
		return (userRepository.findByNickname(nickname).isEmpty()
				&& partnerRepository.findByNickname(nickname).isEmpty());
	}

	@Override
	public SignInRespDto signIn(SignInReqDto dto) {
		Member member = memberRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(member.getUsername(), dto.getPassword())
		);

		return SignInRespDto.builder()
				.role(member.getRole().toString())
				.accessToken(tokenProvider.getAccessToken(member.getUuid(), member.getRole().toString()))
				.refreshToken(tokenProvider.gerRefreshToken(member.getUuid(), member.getRole().toString()))
				.uuid(member.getUuid())
				.build();
	}

	// 회원 공통 정보 저장 (유저, 파트너)
	private Member createMember(SignUpReqDto dto, Role role) {

		Optional<Member> member = memberRepository.findByPhone(dto.getPhone());

		if(member.isPresent()) {
			return memberRepository.save(Member.builder()
					.id(member.get().getId())
					.uuid(member.get().getUuid())
					.email(dto.getEmail())
					.password(encoder.encode(dto.getPassword()))
					.name(dto.getName())
					.birth(dto.getBirth())
					.phone(dto.getPhone())
					.gender(dto.getGender())
					.role(role)
					.status((short)1)
					.build());
		}

		return memberRepository.save(Member.builder()
				.email(dto.getEmail())
				.password(encoder.encode(dto.getPassword()))
				.name(dto.getName())
				.birth(dto.getBirth())
				.phone(dto.getPhone())
				.gender(dto.getGender())
				.uuid(UUID.randomUUID().toString())
				.role(role)
				.status((short)1)
				.build());
	}

	// 회원가입 시, 유저 정보 저장
	private void createUser(String uuid, String nickname) {
		userRepository.save(User.builder()
				.uuid(uuid)
				.nickname(nickname)
				.build());
	}

	// 회원가입 시, 파트너 정보 저장
	private void createPartner(String uuid, String nickname) {
		partnerRepository.save(Partner.builder()
				.uuid(uuid)
				.nickname(nickname)
				.build());
	}

	// 선호 스타일 저장
	private void insertFavoriteStyle(String uuid, List<Long> styles) {
		styles.stream()
				.forEach(i -> favoriteStyleRepository.save(
						FavoriteStyle.builder()
								.uuid(uuid)
								.styleId(i)
								.build()));
	}

	// 중복 회원 검증 : 탈퇴 회원 포함 x
	private boolean isDuplicate(String phone) {
		return memberRepository.findByActivePhone(phone).isPresent();
	}

	// 인증번호 검증
	private boolean isVerify(SmsReqDto dto) {
		return smsRepository.hasKey(dto.getPhone()) &&
				smsRepository.getSmsCode(dto.getPhone())
						.equals(dto.getCode());
	}
}
