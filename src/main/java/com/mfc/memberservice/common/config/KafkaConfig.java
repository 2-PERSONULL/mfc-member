package com.mfc.memberservice.common.config;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mfc.memberservice.member.dto.kafka.DeleteProfileDto;
import com.mfc.memberservice.member.dto.kafka.InsertProfileDto;

@Configuration
public class KafkaConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String BOOTSTRAP_SERVER;

	@Value("${spring.kafka.consumer.group-id}")
	private String GROUP_ID;

	@Bean
	public ConsumerFactory<String, InsertProfileDto> insertProfileConsumer() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		configs.put(GROUP_ID_CONFIG, GROUP_ID);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(
				configs,
				new StringDeserializer(),
				new JsonDeserializer<>(InsertProfileDto.class, false)
		);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, InsertProfileDto> insertProfileListener() {
		ConcurrentKafkaListenerContainerFactory<String, InsertProfileDto> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(insertProfileConsumer());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, DeleteProfileDto> deleteProfileConsumer() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		configs.put(GROUP_ID_CONFIG, GROUP_ID);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(
				configs,
				new StringDeserializer(),
				new JsonDeserializer<>(DeleteProfileDto.class, false)
		);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, DeleteProfileDto> deleteProfileListener() {
		ConcurrentKafkaListenerContainerFactory<String, DeleteProfileDto> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(deleteProfileConsumer());
		return factory;
	}
}
