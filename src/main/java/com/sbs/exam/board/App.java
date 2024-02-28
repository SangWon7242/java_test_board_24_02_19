package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;

import java.util.Scanner;

public class App {
  public void run() {
    System.out.println("== 자바 텍스트 게시판 0.1v ==");
    System.out.println("== 자바 텍스트 게시판 시작 ==");

    Scanner sc = Container.sc;

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.articleController.actionWrite(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.articleController.showList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.articleController.showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.articleController.actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.articleController.actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.memberController.actionJoin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.memberController.actionLogin(rq);
      } else if (rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");
    sc.close();
  }
}
