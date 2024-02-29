package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.member.dto.Member;
import com.sbs.exam.board.session.Session;

import java.util.Scanner;

public class App {
  public void run() {
    System.out.println("== 자바 텍스트 게시판 0.1v ==");
    System.out.println("== 자바 텍스트 게시판 시작 ==");

    Scanner sc = Container.sc;
    Session session = Container.getSession();

    while (true) {
      Member loginedMember = (Member)session.getAttribute("loginedMember");

      String promptName = "명령";

      if(loginedMember != null) {
        promptName = loginedMember.getLoginId();
      }

      System.out.printf("%s) ", promptName);
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.getArticleController().actionWrite(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.getArticleController().showList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.getArticleController().showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.getArticleController().actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.getArticleController().actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.getMemberController().actionJoin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.getMemberController().actionLogin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/logout")) {
        Container.getMemberController().actionLogout(rq);
      } else if (rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");
    sc.close();
  }
}
