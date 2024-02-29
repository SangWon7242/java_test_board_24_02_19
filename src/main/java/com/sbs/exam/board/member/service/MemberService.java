package com.sbs.exam.board.member.service;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.member.dto.Member;
import com.sbs.exam.board.member.repository.MemberRepository;

public class MemberService {
  public MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.getMemberRepository();
  }

  public void makeTestData() {
    for(int i = 1; i <= 3; i++) {
      String loginId = "user" + i;
      String loginPw = "user" + i;
      String name = "이름" + i;

      join(loginId, loginPw, name);
    }
  }

  public int join(String loginId, String loginPw, String name) {
    return memberRepository.join(loginId, loginPw, name);
  }

  public Member findById(int id) {
    return memberRepository.findById(id);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }
}
