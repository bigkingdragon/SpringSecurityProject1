package com.netsong7.test1;

import org.springframework.security.access.prepost.PreAuthorize;

public interface MemberDAO {
	// 회원 가입
	public void addUser(MemberVO member);

	// 로그인
	public MemberVO login(MemberVO member);

	// 내 정보 수정
	@PreAuthorize("#user.userid == principal.userid or hasRole('ROLE_ADMIN')")
	public int editAccount(MemberVO member);

	// 비밀번호 변경
	@PreAuthorize("#userid == principal.userid or hasRole('ROLE_ADMIN')")
	public int changePasswd(String currentPasswd, String newPasswd, String userid);

	// 탈퇴
	@PreAuthorize("#user.userid == principal.userid or hasRole('ROLE_ADMIN')")
	public void bye(String userid);
}
