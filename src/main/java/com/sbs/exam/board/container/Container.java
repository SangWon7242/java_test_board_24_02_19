package com.sbs.exam.board.container;

import com.sbs.exam.board.article.ArticleController;
import com.sbs.exam.board.member.MemberController;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  @Getter
  private static MemberController memberController;
  @Getter
  private static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberController = new MemberController();
    articleController = new ArticleController();
  }

  public static Session getSession() {
    return session;
  }
}
