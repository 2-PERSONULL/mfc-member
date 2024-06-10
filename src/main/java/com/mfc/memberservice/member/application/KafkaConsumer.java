package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.kafka.ProfileDto;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KafkaConsumer {
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;
	private final FavoriteStyleRepository favoriteStyleRepository;

	@KafkaListener(topics = "profile")
	public void insertProfile(ProfileDto dto) {
		if(dto.getRole().equals("USER")) { // 유저
			userRepository.save(User.builder()
					.uuid(dto.getUuid())
					.nickname(dto.getNickname())
					.build());
		} else if(dto.getRole().equals("PARTNER")) { // 파트너
			partnerRepository.save(Partner.builder()
					.uuid(dto.getUuid())
					.nickname(dto.getNickname())
					.build());
		} else { // 이외의 파라미터 : 예외 발생
			throw new BaseException(NO_EXIT_ROLE);
		}

		dto.getFavoriteStyles().stream()
				.forEach(i -> favoriteStyleRepository.save(
						FavoriteStyle.builder()
								.uuid(dto.getUuid())
								.styleId(i)
								.build()));
	}
}
