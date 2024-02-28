package com.sbs.exam.board.container;

import com.sbs.exam.board.article.ArticleController;
import com.sbs.exam.board.member.MemberController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
