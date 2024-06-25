package com.mfc.memberservice.member.dto.resp;

import java.util.List;

import com.mfc.memberservice.member.vo.resp.ProfileRespVo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartnerProfileListRespDto {
	private List<ProfilesDto> profiles;
}
