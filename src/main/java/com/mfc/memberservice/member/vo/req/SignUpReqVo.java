package com.mfc.memberservice.member.vo.req;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpReqVo {
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String phone;
	private LocalDate birth;
	private Short gender;
	private List<Long> favoriteStyles = new ArrayList();
}
