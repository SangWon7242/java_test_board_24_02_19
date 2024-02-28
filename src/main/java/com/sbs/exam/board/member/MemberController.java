package com.sbs.exam.board.member;


import com.sbs.exam.board.Rq;
import com.sbs.exam.board.article.dto.Article;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
  int memberLastId;
  List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).id;
    }
  }

  public void actionJoin(Rq rq) {
    System.out.println("== 회원가입 ==");
    String loginId;
    String loginPw;
    String loginPwConfirm;
    String name;

    // 로그인 아이디 유효성 검사 시작
    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = Container.sc.nextLine();

      if(loginId.trim().length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      break;
    }
    // 로그인 아이디 유효성 검사 끝

    // 로그인 비밀번호 유효성 검사 시작
    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      loginPw = Container.sc.nextLine();

      if(loginPw.trim().length() == 0) {
        System.out.println("로그인 비빌번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        loginPwConfirm = Container.sc.nextLine();

        if(loginPwConfirm.trim().length() == 0) {
          System.out.println("로그인 비빌번호 확인을 입력해주세요.");
          continue;
        }

        if(loginPwConfirm.equals(loginPw) == false) {
          System.out.println("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
          continue;
        }

        break;
      }

      break;
    }
    // 로그인 비밀번호 유효성 검사 끝

    while (true) {
      System.out.printf("이름 : ");
      name = Container.sc.nextLine();

      if(name.trim().length() == 0) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }
      break;
    }

    int id = ++memberLastId;

    Member member = new Member(id, loginId, loginPw, name);

    members.add(member);
    System.out.printf("\"%s\"님 회원 가입 되었습니다.\n", name);
  }
}
