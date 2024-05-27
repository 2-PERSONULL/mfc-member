package com.mfc.memberservice.member.vo.resp;

import java.util.List;

import com.mfc.memberservice.member.vo.req.SnsVo;

import lombok.Getter;

@Getter
public class SnsListRespVo {
	private List<SnsVo> sns;
}
