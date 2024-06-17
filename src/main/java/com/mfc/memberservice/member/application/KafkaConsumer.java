package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.kafka.DeleteProfileDto;
import com.mfc.memberservice.member.dto.kafka.InsertProfileDto;
import com.mfc.memberservice.member.dto.kafka.RequestUserInfoDto;
import com.mfc.memberservice.member.dto.resp.UserProfileResponse;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;
import com.mfc.memberservice.member.infrastructure.PartnerRepository;
import com.mfc.memberservice.member.infrastructure.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KafkaConsumer {
	private final UserRepository userRepository;
	private final PartnerRepository partnerRepository;
	private final FavoriteStyleRepository favoriteStyleRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@KafkaListener(topics = "profile-insert", containerFactory = "insertProfileListener")
	public void insertProfile(InsertProfileDto dto) {
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

		dto.getFavoriteStyles()
				.forEach(i -> favoriteStyleRepository.save(
						FavoriteStyle.builder()
								.uuid(dto.getUuid())
								.styleId(i)
								.build()));
	}

	@KafkaListener(topics = "profile-delete", containerFactory = "deleteProfileListener")
	public void deleteProfile(DeleteProfileDto dto) {
		String uuid = dto.getUuid();

		userRepository.deleteByUuid(uuid);
		partnerRepository.deleteByUuid(uuid);
		favoriteStyleRepository.deleteByUuid(uuid);
	}
}
