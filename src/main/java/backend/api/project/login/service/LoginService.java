package backend.api.project.login.service;

import org.springframework.stereotype.Service;

import backend.api.project.login.dto.Member;

@Service
public class LoginService {
	
	/**
	 * 로그인
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Member getMemberInfo(Member member) throws Exception {
		return new Member();
	}

}
