package com.sbs.exam.board.member.repository;

import com.sbs.exam.board.article.dto.Article;
import com.sbs.exam.board.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private int lastId;
  private List<Member> members;

  public MemberRepository() {
    lastId = 0;
    members = new ArrayList<>();
  }

  public int join(String loginId, String loginPw, String name) {
    int id = lastId + 1;
    members.add(new Member(id, loginId, loginPw, name));
    lastId = id;

    return id;
  }

  public Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.getLoginId().equals(loginId)) {
        return member;
      }
    }

    return null;
  }

  public Member findById(int id) {
    for (Member member : members) {
      if (member.getId() == id) {
        return member;
      }
    }

    return null;
  }
}
