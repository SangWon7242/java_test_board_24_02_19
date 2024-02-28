package com.sbs.exam.board.member;

import com.sbs.exam.board.Rq;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.member.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberController {
  int memberLastId;
  List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).id;
    }
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 3)
        .forEach(
            i -> members.add(new Member(i, "user" + i, "user" + i, "이름" + i)
            )
        );
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

      if (loginId.trim().length() == 0) {
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

      if (loginPw.trim().length() == 0) {
        System.out.println("로그인 비빌번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        loginPwConfirm = Container.sc.nextLine();

        if (loginPwConfirm.trim().length() == 0) {
          System.out.println("로그인 비빌번호 확인을 입력해주세요.");
          continue;
        }

        if (loginPwConfirm.equals(loginPw) == false) {
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

      if (name.trim().length() == 0) {
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

  public void actionLogin(Rq rq) {
    boolean isLogined = rq.isLogined("loginedMember");

    if(isLogined) {
      System.out.println("이미 로그인 되어 있습니다.");
      System.out.println("로그 아웃 후 이용해주세요.");
      return;
    }


    System.out.println("== 로그인 ==");
    String loginId;
    String loginPw;
    Member member;

    // 로그인 아이디 유효성 검사 시작
    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = Container.sc.nextLine();

      if (loginId.trim().length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      member = getMemberByLoginId(loginId);

      if (member == null) {
        System.out.println("입력하신 아이디는 없는 아이디입니다.");
        continue;
      }

      break;
    }
    // 로그인 아이디 유효성 검사 끝

    // 로그인 비밀번호 유효성 검사 시작
    int tryLoginPwCount = 0;
    int tryLoginPwMaxCount = 3;

    while (true) {
      if (tryLoginPwCount >= tryLoginPwMaxCount) {
        System.out.println("비밀번호를 다시 확인 후 입력해주세요.");
        return;
      }

      System.out.printf("로그인 비밀번호 : ");
      loginPw = Container.sc.nextLine();

      if (loginPw.trim().length() == 0) {
        System.out.println("로그인 비빌번호를 입력해주세요.");
        continue;
      }

      if (member.loginPw.equals(loginPw) == false) {
        System.out.println("로그인 비밀번호가 일치하지 않습니다.");
        tryLoginPwCount++;

        System.out.printf("비밀번호 틀린 횟수(%d/3)\n", tryLoginPwCount);
        continue;
      }

      break;
    }
    // 로그인 비밀번호 유효성 검사 끝

    rq.setSessionAttr("loginedMember", member); // key, value

    System.out.printf("\"%s\"님 로그인 되었습니다.\n", member.loginId);
  }

  private Member getMemberByLoginId(String loginId) {
    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return member;
      }
    }

    return null;
  }

  public void actionLogout(Rq rq) {
    boolean isLogout = rq.isLogout("loginedMember");

    if(isLogout) {
      System.out.println("이미 로그아웃 상태입니다.");
      return;
    }

    rq.removeSessionAttr("loginedMember");
    System.out.println("로그아웃 되었습니다.");
  }
}
