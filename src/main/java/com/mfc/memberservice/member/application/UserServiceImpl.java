package com.mfc.memberservice.member.application;

import static com.mfc.memberservice.common.response.BaseResponseStatus.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfc.memberservice.common.exception.BaseException;
import com.mfc.memberservice.member.domain.User;
import com.mfc.memberservice.member.dto.req.ModifyUserReqDto;
import com.mfc.memberservice.member.infrastructure.UserRepository;
import com.mfc.memberservice.member.vo.req.ModifyUserReqVo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public void updateProfile(String uuid, ModifyUserReqDto dto) {
		User user = userRepository.findByUuid(uuid)
				.orElseThrow(() -> new BaseException(MEMBER_NOT_FOUND));

		userRepository.save(User.builder()
				.id(user.getId())
				.nickname(user.getNickname())
				.profileImage(user.getProfileImage())
				.imageAlt(user.getImageAlt())
				.height(dto.getHeight())
				.weight(dto.getWeight())
				.bottomSize(dto.getBottomSize())
				.topSize(dto.getTopSize())
				.shoeSize(dto.getShoeSize())
				.bodyType(dto.getBodyType())
				.build()
		);
	}
}
