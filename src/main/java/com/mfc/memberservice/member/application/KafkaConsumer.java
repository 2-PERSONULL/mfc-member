package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import java.time.LocalDate;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.FavoriteStyle;
import com.mfc.memberservice.member.domain.Member;
import com.mfc.memberservice.member.domain.Partner;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.kafka.DeleteProfileDto;
import com.mfc.memberservice.member.dto.kafka.InsertProfileDto;
import com.mfc.memberservice.member.infrastructure.FavoriteStyleRepository;
import com.mfc.memberservice.member.infrastructure.MemberRepository;
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
	private final MemberRepository memberRepository;
	private final KafkaTemplate<String, String> kafkaTemplate;

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
		// 메시지를 파싱하여 필요한 정보 추출
		// 예시: "RequestId: 123, UserId: user123, PartnerId: partner456, Deadline: 2023-06-30"
		String[] parts = message.split(", ");
		Long requestId = Long.parseLong(parts[0].split(": ")[1]);
		String userId = parts[1].split(": ")[1];
		String partnerId = parts[2].split(": ")[1];
		LocalDate deadline = LocalDate.parse(parts[3].split(": ")[1]);

		// userId를 기반으로 사용자 조회
		User user = userRepository.findByUuid(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));
		Member member = memberRepository.findByUuid(userId)
			.orElseThrow(() -> new RuntimeException("Member not found"));

		// 메시지 생성
		String userProfileMessage = String.format(
			"RequestId: %d, UserId: %s, PartnerId: %s, Deadline: %s, UserImageUrl: %s, UserNickName: %s, UserGender: %d, UserBirth: %s",
			requestId,
			userId,
			partnerId,
			deadline,
			user.getProfileImage(),
			user.getNickname(),
			member.getGender(),
			member.getBirth()
		);

		// 새로운 토픽으로 메시지 전송
		kafkaTemplate.send("user-info-response", userProfileMessage);
	}
}
