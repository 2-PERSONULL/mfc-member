package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import java.time.LocalDate;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.kafka.DeleteProfileDto;
import com.mfc.memberservice.member.dto.kafka.InsertProfileDto;
import com.mfc.memberservice.member.dto.kafka.RequestMessage;
import com.mfc.memberservice.member.dto.kafka.ResponseMessage;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
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
	private final MemberRepository memberRepository;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

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

	@KafkaListener(topics = "request-history-create", groupId = "request-history-group")
	@Transactional
	public void sendUserProfile(String message) {
		try {
			// JSON 메시지를 Java 객체로 변환
			RequestMessage requestMessage = objectMapper.readValue(message, RequestMessage.class);

			// userId를 기반으로 사용자 조회
			User user = userRepository.findByUuid(requestMessage.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
			Member member = memberRepository.findByUuid(requestMessage.getUserId())
				.orElseThrow(() -> new RuntimeException("Member not found"));

			// 메시지 생성
			ResponseMessage responseMessage = ResponseMessage.builder().
				requestId(requestMessage.getRequestId()).
				userId(requestMessage.getUserId()).
				partnerId(requestMessage.getPartnerId()).
				deadline(requestMessage.getDeadline()).
				userImageUrl(user.getProfileImage()).
				userNickName(user.getNickname()).
				userGender(member.getGender()).
				userBirth(member.getBirth()).
				build();

			// 새로운 토픽으로 메시지 전송
			kafkaTemplate.send("user-info-response", objectMapper.writeValueAsString(responseMessage));
		} catch (JsonProcessingException e) {
			// JSON 파싱 오류 처리
			log.error("Failed to parse JSON message: {}", message, e);
		}
	}
}
