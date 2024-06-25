package com.mfc.memberservice.member.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mfc.memberservice.member.dto.kafka.PartnerSummaryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void createPartner(PartnerSummaryDto dto) {
		kafkaTemplate.send("create-partner", dto);
	}
}
