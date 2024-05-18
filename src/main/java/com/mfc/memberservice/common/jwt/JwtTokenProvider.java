package com.mfc.memberservice.common.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mfc.memberservice.common.config.RedisConfig;
import com.mfc.memberservice.member.domain.Member;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	private final Key key;
	private final long accessExp;
	private final long refreshExp;

	public JwtTokenProvider(
			@Value("${jwt.secret-key}") String key,
			@Value("${jwt.access_exp}") long accessExp,
			@Value("${jwt.refresh_exp}") long refreshExp,
			RedisConfig redisTemplate) {
		byte[] keyBytes = Decoders.BASE64.decode(key);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessExp = accessExp;
		this.refreshExp = refreshExp;
	}

	// access token 발급
	public String getAccessToken(Member member) {
		return createToken(member, accessExp);
	}

	// refresh token 발급
	public String gerRefreshToken(Member member) {
		String refresh = createToken(member, refreshExp);
		return refresh;
	}

	// token 발급
	public String createToken(Member member, long exp) {
		return Jwts.builder()
				.claim("role", member.getRole().toString())
				.subject(member.getUuid().toString())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + exp))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
}
