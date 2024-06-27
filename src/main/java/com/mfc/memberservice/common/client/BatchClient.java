package com.mfc.memberservice.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "batch-service")
public interface BatchClient {
	@GetMapping("/batch/ranking")
	PartnerRankingResponse getPartnerRanking();
}
